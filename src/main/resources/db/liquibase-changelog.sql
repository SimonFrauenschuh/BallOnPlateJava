CREATE TABLE IF NOT EXISTS public.position
(
    id SERIAL PRIMARY KEY,
    xest integer NOT NULL,
    yest integer NOT NULL,
    xreal integer NOT NULL,
    yreal integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.result
(
    id SERIAL PRIMARY KEY,
    result integer NOT NULL,
    mode integer NOT NULL,
    error integer
);

INSERT INTO position(xest, yest, xreal, yreal)
VALUES (150, 150, 182, 116);

INSERT INTO result(result, mode)
VALUES (100, 1);