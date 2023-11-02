# FitSystem

FitSystem is a health and fitness management application designed to help track and manage client health metrics.

```Prerequisites
Docker installed on your machine.
```

## Database Configuration

Before using FitSystem, you need to set up and configure the database.
Step 1: Run MySQL Docker Container

Run the following command to start a MySQL container, where `C:/my-sql-path` is the path to the directory where you want to store the database files on your host machine (Windows as example),
and `/var/lib/mysql` is the path where the database files will be stored inside the container.

```bash
    docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=my-password -v C:/my-sql-path:/var/lib/mysql -p 3306:3306 mysql/mysql-server
```

Inside the container, you can connect to the MySQL server using the following command, it will grant all root functionalities to the user root from any host (i.e., %):

```bash
    CREATE USER 'root'@'%' IDENTIFIED BY '123456';
    GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'; 
    FLUSH PRIVILEGES;
```

This will pull the MySQL image (if not already present) and run it in a container named mysql. The root password for the MySQL server is set to 123456, and the data will be stored in D:/DBs/mysql on your host machine.
Step 2: Set Up Database Tables

Once the MySQL server is running, you can set up the required tables for FitSystem.

Connect to your MySQL server using a client of your choice (e.g., MySQL Workbench, CLI) and run the following SQL commands:


```sql
CREATE DATABASE fitsystem;

USE fitsystem;

-- Admin table
CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Client personal information table
CREATE TABLE client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender ENUM('Male', 'Female', 'Other') NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address TEXT NOT NULL
);

-- Client health metrics table
CREATE TABLE client_health_metrics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    weight DECIMAL(5,2) NOT NULL,
    height DECIMAL(5,2) NOT NULL,
    imc_value DECIMAL(5,2) NOT NULL,
    date_recorded DATE NOT NULL DEFAULT (CURRENT_DATE),
    FOREIGN KEY (client_id) REFERENCES client(id)
);
```