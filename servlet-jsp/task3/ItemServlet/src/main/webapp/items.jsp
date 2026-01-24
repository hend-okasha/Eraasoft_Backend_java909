<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, model.Item" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Items Database</title>
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
            padding: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        h1 {
            text-align: center;
            color: white;
            font-size: 3em;
            margin-bottom: 40px;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
            animation: fadeInDown 0.8s ease;
        }

        .section {
            background: white;
            border-radius: 15px;
            padding: 25px;
            margin-bottom: 30px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            animation: fadeInUp 0.8s ease;
        }

        h2 {
            color: #667eea;
            font-size: 1.8em;
            margin-bottom: 20px;
            border-bottom: 3px solid #667eea;
            padding-bottom: 10px;
        }

        ul {
            list-style: none;
        }

        li {
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            padding: 15px 20px;
            margin-bottom: 10px;
            border-radius: 8px;
            border-left: 5px solid #667eea;
            transition: all 0.3s ease;
            font-size: 1.1em;
        }

        li:hover {
            transform: translateX(10px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
            border-left-color: #764ba2;
        }

        .empty {
            color: #999;
            font-style: italic;
            text-align: center;
            padding: 20px;
        }

        @keyframes fadeInDown {
            from {
                opacity: 0;
                transform: translateY(-50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(50px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .badge {
            display: inline-block;
            background: #667eea;
            color: white;
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 0.9em;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🗄️ Items Database</h1>

        <div class="section">
            <h2>📋 All Items <span class="badge">Full List</span></h2>
            <ul>
            <%
                ArrayList<Item> allItems = (ArrayList<Item>) request.getAttribute("allItems");
                if (allItems != null && !allItems.isEmpty()) {
                    for (Item item : allItems) {
            %>
                        <li>✨ <%= item %></li>
            <%
                    }
                } else {
            %>
                    <li class="empty">No items found</li>
            <%
                }
            %>
            </ul>
        </div>

        <div class="section">
            <h2>🔢 Items with ID 1 or 2 <span class="badge">Filtered</span></h2>
            <ul>
            <%
                ArrayList<Item> byId = (ArrayList<Item>) request.getAttribute("byId");
                if (byId != null && !byId.isEmpty()) {
                    for (Item item : byId) {
            %>
                        <li>🎯 <%= item %></li>
            <%
                    }
                } else {
            %>
                    <li class="empty">No items found</li>
            <%
                }
            %>
            </ul>
        </div>

        <div class="section">
            <h2>🔤 Items where Name contains 'i' <span class="badge">Search</span></h2>
            <ul>
            <%
                ArrayList<Item> byName = (ArrayList<Item>) request.getAttribute("byName");
                if (byName != null && !byName.isEmpty()) {
                    for (Item item : byName) {
            %>
                        <li>📝 <%= item %></li>
            <%
                    }
                } else {
            %>
                    <li class="empty">No items found</li>
            <%
                }
            %>
            </ul>
        </div>

        <div class="section">
            <h2>💰 Items where Price > 50 OR < 20 <span class="badge">Price Range</span></h2>
            <ul>
            <%
                ArrayList<Item> byPrice = (ArrayList<Item>) request.getAttribute("byPrice");
                if (byPrice != null && !byPrice.isEmpty()) {
                    for (Item item : byPrice) {
            %>
                        <li>💵 <%= item %></li>
            <%
                    }
                } else {
            %>
                    <li class="empty">No items found</li>
            <%
                }
            %>
            </ul>
        </div>
    </div>
</body>
</html>
