# 48shop E-Commerce Platform
## Installation
ไม่ต้องใช้ ตัวติดตั้งใดๆ เพราะเราทำการ deploy ไปไว้อยู่บน ibm bluemix เป็นที่เรียบร้อย <br>
สามารถมาดูตัวหน้าเว็บไซต์ E-commerce ได้ที่ https://48shop.mybluemix.net/

# API Design
## Address
### GET /addresses
Return list of all addresses in database <br>

### GET /address/{id} 
Return address specific by address id <br>

### POST /address
Use to create new address <br>

### DELETE /address/{id}
Use to delete address

## Order
### GET /orders
Return list of all order

### GET /order/{id}
Use to get order from specific id

### POST /order
Use to order a product

### DELETE /order/{id}
Use to delete order by specific id

## Product
### GET /products
Return list of all products

### GET /product/{id}
Use to get product with specific id

### POST /product
Use to add product to database

### DELETE /product/{id}
Use to delete product from database



