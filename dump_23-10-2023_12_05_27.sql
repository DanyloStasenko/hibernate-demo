--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Drop databases (except postgres and template1)
--

DROP DATABASE "postgresDB";




--
-- Drop roles
--

DROP ROLE "user";


--
-- Roles
--

CREATE ROLE "user";
ALTER ROLE "user" WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:bcQV0nsIrXwbip0Y9DdUng==$bokyWAWu3mNn3lrP/jPREJLiKUZoXUzr4/AjFdVw9SQ=:63tG2vwkG6EifsIyGZiSqfBWtyV90S4ec16JX4Rb8yQ=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.0 (Debian 16.0-1.pgdg120+1)

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

UPDATE pg_catalog.pg_database SET datistemplate = false WHERE datname = 'template1';
DROP DATABASE template1;
--
-- Name: template1; Type: DATABASE; Schema: -; Owner: user
--

CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE template1 OWNER TO "user";

\connect template1

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

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: user
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: template1; Type: DATABASE PROPERTIES; Schema: -; Owner: user
--

ALTER DATABASE template1 IS_TEMPLATE = true;


\connect template1

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

--
-- Name: DATABASE template1; Type: ACL; Schema: -; Owner: user
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.0 (Debian 16.0-1.pgdg120+1)

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

DROP DATABASE postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: user
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE postgres OWNER TO "user";

\connect postgres

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

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: user
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- PostgreSQL database dump complete
--

--
-- Database "postgresDB" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.0 (Debian 16.0-1.pgdg120+1)

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

--
-- Name: postgresDB; Type: DATABASE; Schema: -; Owner: user
--

CREATE DATABASE "postgresDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE "postgresDB" OWNER TO "user";

\connect "postgresDB"

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

--
-- Name: address; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.address (
    id text NOT NULL,
    address1 text,
    address2 text,
    street text,
    city text,
    state text,
    country text,
    user_id text,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone
);


ALTER TABLE public.address OWNER TO "user";

--
-- Name: post; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.post (
    id bigint NOT NULL,
    title character varying(255)
);


ALTER TABLE public.post OWNER TO "user";

--
-- Name: post_comment; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.post_comment (
    id bigint NOT NULL,
    post_id bigint,
    review character varying(255)
);


ALTER TABLE public.post_comment OWNER TO "user";

--
-- Name: user_details; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.user_details (
    id text NOT NULL,
    name text NOT NULL,
    email text,
    mobile_number text,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone
);


ALTER TABLE public.user_details OWNER TO "user";

--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.address (id, address1, address2, street, city, state, country, user_id, created_at, updated_at) FROM stdin;
3	address1_john_carter	address2_john_carter	street_john_carter	Barcelona	Catalonia	Spain	30d592b4-a04d-4fc9-a4b2-58322daa6e56	2023-10-20 08:32:16.741975	\N
2	address1_john_1	address2_john_1	street_john_1	Corte	Corsica	France	a0026a40-3b8e-4ab6-a110-799eefa5776e	2023-10-20 08:32:05.260971	\N
1	address1_john	address2_john	street_john	Munich	Bavaria	Germany	1	2023-10-20 08:32:01.775682	\N
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.post (id, title) FROM stdin;
2	High-Performance Java Persistence - Part 2
3	High-Performance Java Persistence - Part 3
4	High-Performance Java Persistence - Part 4
1	First Post
\.


--
-- Data for Name: post_comment; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.post_comment (id, post_id, review) FROM stdin;
1	1	Excellent book to understand Java Persistence
2	2	Must-read for Java developers
3	3	Five Stars
4	4	A great reference book
\.


--
-- Data for Name: user_details; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.user_details (id, name, email, mobile_number, created_at, updated_at) FROM stdin;
1	John	john_1@gmail.com	_919876543219	2023-10-20 08:29:39.033242	\N
2	John Carter	john_carter@gmail.com	_919876543218	2023-10-20 08:29:55.236541	\N
a0026a40-3b8e-4ab6-a110-799eefa5776e	name	mail	+123	2023-10-20 11:43:27.732351	\N
30d592b4-a04d-4fc9-a4b2-58322daa6e56	name	mail	+123	2023-10-20 11:44:07.665419	\N
40a08aad-d3eb-4df0-b051-2717aef2febc	name	mail	+123	2023-10-20 18:56:39.850245	\N
\.


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: post_comment post_comment_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.post_comment
    ADD CONSTRAINT post_comment_pkey PRIMARY KEY (id);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (id);


--
-- Name: address fk_address_1; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fk_address_1 FOREIGN KEY (user_id) REFERENCES public.user_details(id);


--
-- Name: post_comment fkna4y825fdc5hw8aow65ijexm0; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.post_comment
    ADD CONSTRAINT fkna4y825fdc5hw8aow65ijexm0 FOREIGN KEY (post_id) REFERENCES public.post(id);


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

