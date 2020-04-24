SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

-- Database: corre

-- DROP DATABASE corre;

CREATE DATABASE corre
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE corre
    IS 'Banco da aplicacao Corre';

CREATE TABLE public.aluno (
    id bigint NOT NULL,
    data_nascimento timestamp without time zone,
    deletado boolean NOT NULL,
    nome character varying(255),
    ra bigint
);


ALTER TABLE public.aluno OWNER TO postgres;

CREATE TABLE public.categoriausuario (
    id bigint NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.categoriausuario OWNER TO postgres;

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

CREATE TABLE public.materia (
    id bigint NOT NULL,
    data_atualizacao timestamp without time zone,
    data_cadastro timestamp without time zone,
    descricao character varying(255),
    nome character varying(255)
);


ALTER TABLE public.materia OWNER TO postgres;

CREATE TABLE public.pessoa (
    id bigint NOT NULL,
    data_nascimento timestamp without time zone,
    deletado boolean NOT NULL,
    nome character varying(255)
);


ALTER TABLE public.pessoa OWNER TO postgres;

CREATE TABLE public.produto (
    id bigint NOT NULL,
    fabricante character varying(255),
    nome character varying(255),
    valor numeric(19,2)
);


ALTER TABLE public.produto OWNER TO postgres;


CREATE TABLE public.skill (
    id bigint NOT NULL,
    deletado boolean NOT NULL,
    nome character varying(255) NOT NULL,
    dataatualizacao timestamp without time zone,
    datacriacao timestamp without time zone,
    descricao character varying(255)
);


ALTER TABLE public.skill OWNER TO postgres;

-
CREATE TABLE public.skillsimpler (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.skillsimpler OWNER TO postgres;

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    categoria_usuario integer,
    data_atualizacao timestamp without time zone,
    data_cadastro timestamp without time zone,
    deletado boolean NOT NULL,
    login character varying(255),
    senha character varying(255),
    pessoa_id bigint
);


ALTER TABLE public.usuario OWNER TO postgres;

ALTER TABLE ONLY public.aluno
    ADD CONSTRAINT aluno_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.categoriausuario
    ADD CONSTRAINT categoriausuario_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.materia
    ADD CONSTRAINT materia_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.skill
    ADD CONSTRAINT skill_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.skillsimpler
    ADD CONSTRAINT skillsimpler_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
