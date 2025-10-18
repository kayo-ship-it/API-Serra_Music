# API-Serra_Music
API RESTful desenvolvida em Spring Boot para gerenciamento de usuários, artistas, músicas e playlists. O projeto implementa relacionamentos complexos usando JPA/Hibernate, validação de dados com Bean Validation e documentação automática com Swagger/Springdoc OpenAPI.
Funcionalidades principais:
CRUD completo para Usuários, Perfis, Artistas, Músicas e Playlists
Associação automática de Perfil ao criar um usuário
Associação de músicas a playlists e artistas, respeitando relacionamentos ManyToMany
Endpoints robustos com tratamento de exceções centralizado
Validação de campos obrigatórios e formatos corretos
Documentação detalhada de endpoints com Swagger
Tecnologias utilizadas:
Java 17
Spring Boot
Spring Data JPA / Hibernate
PostgreSQL
Bean Validation
Swagger / Springdoc OpenAPI
