-- Agregar campos de auditoría a la tabla ventas
ALTER TABLE ventas 
ADD COLUMN IF NOT EXISTS CREADO_POR VARCHAR(100),
ADD COLUMN IF NOT EXISTS FECHA_CREACION TIMESTAMP,
ADD COLUMN IF NOT EXISTS MODIFICADO_POR VARCHAR(100),
ADD COLUMN IF NOT EXISTS FECHA_MODIFICACION TIMESTAMP;

-- Actualizar la tabla auditorías para usar el enum y agregar ID_REGISTRO
ALTER TABLE auditorias
ALTER COLUMN OPERACION TYPE VARCHAR(20),
ADD COLUMN IF NOT EXISTS ID_REGISTRO BIGINT;

-- Comentarios para documentación
COMMENT ON COLUMN ventas.CREADO_POR IS 'Usuario que creó el registro';
COMMENT ON COLUMN ventas.FECHA_CREACION IS 'Fecha y hora de creación';
COMMENT ON COLUMN ventas.MODIFICADO_POR IS 'Usuario que modificó el registro';
COMMENT ON COLUMN ventas.FECHA_MODIFICACION IS 'Fecha y hora de modificación';
COMMENT ON COLUMN auditorias.ID_REGISTRO IS 'ID del registro al que hace referencia la auditoría';
