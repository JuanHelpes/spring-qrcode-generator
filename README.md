# QR Code Generator

Aplicação em **Spring Boot** que gera QR Codes a partir de um texto e armazena o resultado no **Supabase Storage**.  
O QR Code é gerado usando a biblioteca **Google ZXing** e a API retorna o link do PNG salvo.

## 🚀 Tecnologias

- Java 21 + Spring Boot
- Maven
- Supabase (Storage Bucket)
- Docker
- Google ZXing (QR Code)

## ⚙️ Pré-requisitos

- Java 21 JDK
- Maven
- Docker
- Conta no Supabase com **URL** e **API Key** válidos

## 🔑 Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto:

````env
SUPABASE_URL=your_supabase_url
SUPABASE_API_KEY=your_supabase_api_key
SUPABASE_BUCKET=qrCode-generator

## ▶️ Rodando a aplicação

mvn clean package
mvn spring-boot:run

#### Docker Deployment

1. Build da imagem:
   ```bash
   docker build -t qrcode-generator:X.X .
````

2. Run the container:
   ```bash
   docker run --env-file .env -p 8080:8080 qrcode-generator:X.X
   ```
   > Remember to replace the .env file path to the path of your environment file that you created.

## 📡 API

POST /qrcode

Gera um QR Code a partir de um texto e salva no Supabase.
Retorna o link público do arquivo.

**Request**

```json
{
  "text": "https://example.com"
}
```

**Response**

```json
{
  "url": "https://your-project.supabase.co/storage/v1/object/public/qrCode-generator/uuid.png"
}
```
