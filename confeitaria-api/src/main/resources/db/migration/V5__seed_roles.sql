-- Roles iniciais do sistema
INSERT INTO roles (id, name, description, created_at, updated_at)
VALUES
    (gen_random_uuid(), 'CLIENTES', 'FUNÇÃO DE CLIENTE PADRÃO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'ADMIN', 'ADMINISTRADOR DO SISTEMA', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'FUNCIONARIOS', 'FUNÇÃO DE FUNCIONÁRIO PADRÃO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (name) DO NOTHING;
-- evita erro se já existir
