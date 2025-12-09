# Timekeeping API Documentation

## Cấu hình Database

Cập nhật thông tin database trong file `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/timekeeping
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Chạy ứng dụng

```bash
./mvnw spring-boot:run
```

## API Endpoints

### Authentication

#### 1. Login
```
POST /api/auth/login
Content-Type: application/json

Request Body:
{
  "email": "user@example.com",
  "password": "password123"
}

Response:
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "email": "user@example.com",
  "fullName": "John Doe"
}
```

### Users API

#### 1. Get All Users
```
GET /api/users
Authorization: Bearer {token}

Response:
[
  {
    "id": "uuid",
    "email": "user@example.com",
    "fullName": "John Doe",
    "status": "ACTIVE",
    "createdAt": "2024-01-01T10:00:00"
  }
]
```

#### 2. Get User by ID
```
GET /api/users/{id}
Authorization: Bearer {token}
```

#### 3. Create User
```
POST /api/users
Authorization: Bearer {token}
Content-Type: application/json

Request Body:
{
  "email": "newuser@example.com",
  "password": "password123",
  "fullName": "Jane Doe",
  "status": "ACTIVE"
}
```

#### 4. Update User
```
PUT /api/users/{id}
Authorization: Bearer {token}
Content-Type: application/json

Request Body:
{
  "email": "updated@example.com",
  "password": "newpassword123",
  "fullName": "Jane Updated",
  "status": "BLOCKED"
}
```

#### 5. Delete User
```
DELETE /api/users/{id}
Authorization: Bearer {token}
```

### Roles API

#### 1. Get All Roles
```
GET /api/roles
Authorization: Bearer {token}
```

#### 2. Get Role by ID
```
GET /api/roles/{id}
Authorization: Bearer {token}
```

#### 3. Create Role
```
POST /api/roles
Authorization: Bearer {token}
Content-Type: application/json

Request Body:
{
  "name": "SYSTEM_ADMIN"
}
```

#### 4. Update Role
```
PUT /api/roles/{id}
Authorization: Bearer {token}
Content-Type: application/json

Request Body:
{
  "name": "MANAGER"
}
```

#### 5. Delete Role
```
DELETE /api/roles/{id}
Authorization: Bearer {token}
```

### Permissions API

#### 1. Get All Permissions
```
GET /api/permissions
Authorization: Bearer {token}
```

#### 2. Get Permission by ID
```
GET /api/permissions/{id}
Authorization: Bearer {token}
```

#### 3. Create Permission
```
POST /api/permissions
Authorization: Bearer {token}
Content-Type: application/json

Request Body:
{
  "code": "EXPORT_REPORT",
  "description": "Permission to export reports"
}
```

#### 4. Update Permission
```
PUT /api/permissions/{id}
Authorization: Bearer {token}
Content-Type: application/json

Request Body:
{
  "code": "MANAGE_USER",
  "description": "Permission to manage users"
}
```

#### 5. Delete Permission
```
DELETE /api/permissions/{id}
Authorization: Bearer {token}
```

### Role-Permissions API

#### 1. Get All Role-Permissions
```
GET /api/role-permissions
Authorization: Bearer {token}
```

#### 2. Get Role-Permission by ID
```
GET /api/role-permissions/{id}
Authorization: Bearer {token}
```

#### 3. Get Permissions by Role
```
GET /api/role-permissions/role/{roleId}
Authorization: Bearer {token}
```

#### 4. Create Role-Permission
```
POST /api/role-permissions
Authorization: Bearer {token}
Content-Type: application/json

Request Body:
{
  "roleId": "role-uuid",
  "permissionId": "permission-uuid"
}
```

#### 5. Delete Role-Permission
```
DELETE /api/role-permissions/{id}
Authorization: Bearer {token}
```

#### 6. Delete Role-Permission by Role and Permission
```
DELETE /api/role-permissions/role/{roleId}/permission/{permissionId}
Authorization: Bearer {token}
```

## Ví dụ sử dụng với cURL

### 1. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@example.com",
    "password": "admin123"
  }'
```

### 2. Create User (với token)
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -d '{
    "email": "newuser@example.com",
    "password": "password123",
    "fullName": "New User",
    "status": "ACTIVE"
  }'
```

### 3. Get All Users
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

## Lưu ý

1. Tất cả các endpoint (trừ `/api/auth/login`) đều yêu cầu JWT token trong header Authorization
2. Token có thời gian hết hạn là 24 giờ (có thể thay đổi trong `application.properties`)
3. Mật khẩu được mã hóa bằng BCrypt trước khi lưu vào database
4. UUID được sử dụng làm primary key cho tất cả các bảng
5. Đổi `jwt.secret` trong `application.properties` thành một chuỗi bảo mật của riêng bạn

## Cấu trúc Project

```
src/main/java/com/timekeeping/timekeeping/
├── controller/          # REST API Controllers
├── dto/                 # Data Transfer Objects
├── entity/              # JPA Entities
├── exception/           # Exception Handlers
├── repository/          # JPA Repositories
├── security/            # Security & JWT Configuration
└── service/             # Business Logic Services
```
