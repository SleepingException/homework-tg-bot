CREATE TABLE public.days
(
    id integer NOT NULL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    date date,
    CONSTRAINT days_pkey PRIMARY KEY (name)
)

TABLESPACE pg_default;

ALTER TABLE public.days
    OWNER to postgres;

CREATE TABLE public.subjects
(
    id integer NOT NULL DEFAULT nextval((1)::regclass),
    day_name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    homework text COLLATE pg_catalog."default",
    "time" time without time zone,
    info text COLLATE pg_catalog."default",
    name character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT subjects_pkey PRIMARY KEY (id),
    CONSTRAINT subjects_day_name_fkey FOREIGN KEY (day_name)
        REFERENCES public.days (name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.subjects
    OWNER to postgres;

ALTER TABLE public.days
    ADD COLUMN id integer NOT NULL;
ALTER TABLE public.days
    ADD COLUMN name character varying(20) COLLATE pg_catalog."default" NOT NULL;
ALTER TABLE public.days
    ADD COLUMN date date;