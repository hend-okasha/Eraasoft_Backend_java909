package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class History {
    private String action;
    private Double amount;
    private LocalDateTime actionTime;
    private String status;
    private String description;


    public History() {
    }

    public History(String action, Double amount, String status, String description) {
        this.action = action;
        this.amount = amount;
        this.actionTime = LocalDateTime.now();
        this.status = status;
        this.description = description;
    }

    public History(String action, String status, String description) {
        this.action = action;
        this.amount = null;
        this.actionTime = LocalDateTime.now();
        this.status = status;
        this.description = description;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = actionTime.format(formatter);

        String amountStr;
        if (amount != null) {
            amountStr = String.format("%.2f EGP", amount);
        } else {
            amountStr = "N/A";
        }
        
        return String.format("%-20s | %-10s | %-15s | %s",
                formattedDate,
                action,
                "Status: " + status,
                amount != null ? "Amount: " + amountStr + " - " + description : description);
    }
}
