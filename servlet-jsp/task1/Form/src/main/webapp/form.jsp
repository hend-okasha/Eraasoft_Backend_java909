<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration Form</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.3);
            width: 100%;
            max-width: 500px;
        }
        
        h1 {
            color: #667eea;
            text-align: center;
            margin-bottom: 30px;
            font-size: 28px;
        }
        
        .form-group {
            margin-bottom: 25px;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 600;
            font-size: 14px;
        }
        
        input[type="text"],
        input[type="password"],
        select {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 14px;
            transition: all 0.3s ease;
        }
        
        input[type="text"]:focus,
        input[type="password"]:focus,
        select:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }
        
        .radio-group {
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
        }
        
        .radio-option {
            display: flex;
            align-items: center;
            gap: 8px;
        }
        
        input[type="radio"] {
            width: 18px;
            height: 18px;
            cursor: pointer;
            accent-color: #667eea;
        }
        
        .radio-option label {
            margin: 0;
            font-weight: 400;
            cursor: pointer;
        }
        
        .submit-btn {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            margin-top: 10px;
        }
        
        .submit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
        }
        
        .submit-btn:active {
            transform: translateY(0);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registration Form</h1>
        
        <form action="display.jsp" method="post">
            <!-- Full Name Input -->
            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" placeholder="Enter your full name" required>
            </div>
            
            <!-- Password Input -->
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>
            
            <!-- Age Input -->
            <div class="form-group">
                <label for="age">Age:</label>
                <input type="text" id="age" name="age" placeholder="Enter your age" required>
            </div>
            
            <!-- Address Radio Buttons -->
            <div class="form-group">
                <label>Address (Radio):</label>
                <div class="radio-group">
                    <div class="radio-option">
                        <input type="radio" id="cairo" name="address" value="Cairo" required>
                        <label for="cairo">Cairo</label>
                    </div>
                    <div class="radio-option">
                        <input type="radio" id="alex" name="address" value="Alexandria">
                        <label for="alex">Alexandria</label>
                    </div>
                    <div class="radio-option">
                        <input type="radio" id="menofia" name="address" value="Menofia">
                        <label for="menofia">Menofia</label>
                    </div>
                </div>
            </div>
            
            <!-- City Select Dropdown -->
            <div class="form-group">
                <label for="city">City (Select):</label>
                <select id="city" name="city" required>
                    <option value="">-- Select City --</option>
                    <option value="Cairo">Cairo</option>
                    <option value="Alexandria">Alexandria</option>
                    <option value="Menofia">Menofia</option>
                </select>
            </div>
            
            <!-- Submit Button -->
            <button type="submit" class="submit-btn">Submit</button>
        </form>
    </div>
</body>
</html>

