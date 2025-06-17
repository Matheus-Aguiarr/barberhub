# BarberHub - Sistema de agendamento para barbearias.

Uma API REST feita para gerenciar agendamentos de uma barbearia ficticia.

---
## Tecnologias Utilizadas
- Java 17
- SpringBoot
- Spring Data JPA
- PostgreSQL
- Maven
  
---
## Funcionalidades
### User

| Método | Rota                      | Descrição                        |
|--------|---------------------------|----------------------------------|
| GET    | `/users`                  | Lista todos os users             |
| GET    | `/users/{id}`             | Busca um user por ID             |
| GET    | `/users/email/{userEmail}`| Busca um user pelo email         |
| POST   | `/users`                  | Cria um novo user                |
| PUT    | `/users/{id}`             | Atualiza os dados do user        |
| DELETE | `/users/{id}`             | Deleta um user pelo id           |

### Service (Serviços: Corte de cabelo, Barba, etc.)

| Método | Rota                      | Descrição                        |
|--------|---------------------------|----------------------------------|
| GET    | `/services`               | Lista todos os serviços          |
| GET    | `/services/{id}`          | Busca um serviço por ID          |
| POST   | `/services`               | Cria um novo serviço             |
| PUT    | `/services/{id}`          | Atualiza os dados do serviço     |
| DELETE | `/services/{id}`          | Deleta um serviço pelo id        |

### Appointment (Agendamento)

| Método | Rota                                                      | Descrição                                              |
|--------|-----------------------------------------------------------|--------------------------------------------------------|
| GET    | `/appointment`                                            | Lista todos os agendamentos                            |
| GET    | `/appointment/userId/{userId}`                            | Lista agendamentos de um usuário                       |
| GET    | `/appointment/{appointmentId}`                            | Busca um agendamento pelo ID                           |
| GET    | `/appointment/available-times?serviceId=1&date=YYYY-MM-DD`| Lista horários disponíveis para um serviço na data     |
| POST   | `/appointment`                                            | Cria um novo agendamento                               |
| GET    | `/appointment/date?date=YYYY-MM-DD`                       | Lista agendamentos de uma data específica              |
| GET    | `/appointment/confirmed`                                  | Lista agendamentos com status CONFIRMED                |
| PUT    | `/appointment/{appointmentId}/status?status=STATUS`       | Atualiza o status de um agendamento                    |
| DELETE | `/appointment/{appointmentId}`                            | Deleta um agendamento pelo ID                          |
| DELETE | `/appointment/{appointmentId}/cancel`                     | Cancela um agendamento (altera status para CANCELLED)  |

---

## Como rodar o projeto localmente

1. Clone este repositório:
   ```bash
   git clone https://github.com/Matheus-Aguiarr/barberhub.git
   ```

2. Abra no IntelliJ, Eclipse ou VS Code com suporte Java

3. Rode o projeto 

4. Use o Postman ou Insomnia para testar os endpoints

---

## 🧑‍💻 Autor

**Matheus Aguiar** – Desenvolvedor Java em formação  
🇧🇷 Guarulhos, São Paulo | Desde Dez/2024

---

## 📫 Contato

Se quiser trocar ideia ou dar feedback:

- Email: `matheusaguiardealmeida@gmail.com`
- GitHub: [Matheus-Aguiarr](https://github.com/Matheus-Aguiarr)
- Portfolio: [Portfolio](https://matheusaguiar.vercel.app)

---

## Finalidade
Projeto unicamente com finalidade educacional, sem fins de uso profissional.
