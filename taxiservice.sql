--
-- PostgreSQL database dump
--

-- Dumped from database version 10.13
-- Dumped by pg_dump version 10.13

-- Started on 2020-09-27 12:30:20

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
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 205 (class 1259 OID 98602)
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    admin_id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(150) NOT NULL
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 98600)
-- Name: admin_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.admin_admin_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_admin_id_seq OWNER TO postgres;

--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 204
-- Name: admin_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.admin_admin_id_seq OWNED BY public.admin.admin_id;


--
-- TOC entry 201 (class 1259 OID 98546)
-- Name: car; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car (
    car_id integer NOT NULL,
    licenseplate character varying(50) NOT NULL,
    modelname character varying(50) NOT NULL,
    seatnumber integer NOT NULL,
    location character varying(50) NOT NULL,
    isactive boolean,
    driving_license_number character varying(50)
);


ALTER TABLE public.car OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 98544)
-- Name: car_car_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_car_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_car_id_seq OWNER TO postgres;

--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 200
-- Name: car_car_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.car_car_id_seq OWNED BY public.car.car_id;


--
-- TOC entry 197 (class 1259 OID 98510)
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    client_id integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    email character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(150) NOT NULL,
    phonenumber character varying(70) NOT NULL,
    cardnumber character varying(70)
);


ALTER TABLE public.client OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 98508)
-- Name: client_client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_client_id_seq OWNER TO postgres;

--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 196
-- Name: client_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_client_id_seq OWNED BY public.client.client_id;


--
-- TOC entry 199 (class 1259 OID 98529)
-- Name: driver; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.driver (
    driver_id integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(150) NOT NULL,
    driving_license_number character varying(50) NOT NULL,
    phonenumber character varying(150) NOT NULL,
    isworking boolean
);


ALTER TABLE public.driver OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 98527)
-- Name: driver_driver_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.driver_driver_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.driver_driver_id_seq OWNER TO postgres;

--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 198
-- Name: driver_driver_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.driver_driver_id_seq OWNED BY public.driver.driver_id;


--
-- TOC entry 203 (class 1259 OID 98563)
-- Name: paymenttype; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.paymenttype (
    payment_id integer NOT NULL,
    finishaddress character varying(250)
);


ALTER TABLE public.paymenttype OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 98561)
-- Name: paymenttype_payment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.paymenttype_payment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.paymenttype_payment_id_seq OWNER TO postgres;

--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 202
-- Name: paymenttype_payment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.paymenttype_payment_id_seq OWNED BY public.paymenttype.payment_id;


--
-- TOC entry 207 (class 1259 OID 106498)
-- Name: ride; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ride (
    ride_id integer NOT NULL,
    startaddress character varying(255),
    finishaddress character varying(250),
    starttime timestamp without time zone,
    finishtime timestamp without time zone,
    date timestamp without time zone,
    status character varying(250),
    price character varying(250),
    lat double precision,
    lng double precision,
    driver_id integer,
    client_id integer,
    payment_id integer,
    car_id integer
);


ALTER TABLE public.ride OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 106496)
-- Name: ride_ride_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ride_ride_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ride_ride_id_seq OWNER TO postgres;

--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 206
-- Name: ride_ride_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ride_ride_id_seq OWNED BY public.ride.ride_id;


--
-- TOC entry 2707 (class 2604 OID 98605)
-- Name: admin admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin ALTER COLUMN admin_id SET DEFAULT nextval('public.admin_admin_id_seq'::regclass);


--
-- TOC entry 2705 (class 2604 OID 98549)
-- Name: car car_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car ALTER COLUMN car_id SET DEFAULT nextval('public.car_car_id_seq'::regclass);


--
-- TOC entry 2703 (class 2604 OID 98513)
-- Name: client client_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN client_id SET DEFAULT nextval('public.client_client_id_seq'::regclass);


--
-- TOC entry 2704 (class 2604 OID 98532)
-- Name: driver driver_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.driver ALTER COLUMN driver_id SET DEFAULT nextval('public.driver_driver_id_seq'::regclass);


--
-- TOC entry 2706 (class 2604 OID 98566)
-- Name: paymenttype payment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymenttype ALTER COLUMN payment_id SET DEFAULT nextval('public.paymenttype_payment_id_seq'::regclass);


--
-- TOC entry 2708 (class 2604 OID 106501)
-- Name: ride ride_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ride ALTER COLUMN ride_id SET DEFAULT nextval('public.ride_ride_id_seq'::regclass);


--
-- TOC entry 2736 (class 2606 OID 98609)
-- Name: admin admin_password_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_password_key UNIQUE (password);


--
-- TOC entry 2738 (class 2606 OID 98607)
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (admin_id);


--
-- TOC entry 2728 (class 2606 OID 98555)
-- Name: car car_driving_license_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_driving_license_number_key UNIQUE (driving_license_number);


--
-- TOC entry 2730 (class 2606 OID 98553)
-- Name: car car_location_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_location_key UNIQUE (location);


--
-- TOC entry 2732 (class 2606 OID 98551)
-- Name: car car_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (car_id);


--
-- TOC entry 2710 (class 2606 OID 98526)
-- Name: client client_cardnumber_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_cardnumber_key UNIQUE (cardnumber);


--
-- TOC entry 2712 (class 2606 OID 98520)
-- Name: client client_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_email_key UNIQUE (email);


--
-- TOC entry 2714 (class 2606 OID 98524)
-- Name: client client_phonenumber_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_phonenumber_key UNIQUE (phonenumber);


--
-- TOC entry 2716 (class 2606 OID 98518)
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (client_id);


--
-- TOC entry 2718 (class 2606 OID 98522)
-- Name: client client_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_username_key UNIQUE (username);


--
-- TOC entry 2720 (class 2606 OID 98541)
-- Name: driver driver_driving_license_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.driver
    ADD CONSTRAINT driver_driving_license_number_key UNIQUE (driving_license_number);


--
-- TOC entry 2722 (class 2606 OID 98543)
-- Name: driver driver_phonenumber_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.driver
    ADD CONSTRAINT driver_phonenumber_key UNIQUE (phonenumber);


--
-- TOC entry 2724 (class 2606 OID 98537)
-- Name: driver driver_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.driver
    ADD CONSTRAINT driver_pkey PRIMARY KEY (driver_id);


--
-- TOC entry 2726 (class 2606 OID 98539)
-- Name: driver driver_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.driver
    ADD CONSTRAINT driver_username_key UNIQUE (username);


--
-- TOC entry 2734 (class 2606 OID 98568)
-- Name: paymenttype paymenttype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.paymenttype
    ADD CONSTRAINT paymenttype_pkey PRIMARY KEY (payment_id);


--
-- TOC entry 2740 (class 2606 OID 106506)
-- Name: ride ride_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ride
    ADD CONSTRAINT ride_pkey PRIMARY KEY (ride_id);


--
-- TOC entry 2741 (class 2606 OID 98556)
-- Name: car car_driving_license_number_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_driving_license_number_fkey FOREIGN KEY (driving_license_number) REFERENCES public.driver(driving_license_number);


--
-- TOC entry 2745 (class 2606 OID 106522)
-- Name: ride ride_car_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ride
    ADD CONSTRAINT ride_car_id_fkey FOREIGN KEY (car_id) REFERENCES public.car(car_id);


--
-- TOC entry 2743 (class 2606 OID 106512)
-- Name: ride ride_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ride
    ADD CONSTRAINT ride_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(client_id);


--
-- TOC entry 2742 (class 2606 OID 106507)
-- Name: ride ride_driver_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ride
    ADD CONSTRAINT ride_driver_id_fkey FOREIGN KEY (driver_id) REFERENCES public.driver(driver_id);


--
-- TOC entry 2744 (class 2606 OID 106517)
-- Name: ride ride_payment_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ride
    ADD CONSTRAINT ride_payment_id_fkey FOREIGN KEY (payment_id) REFERENCES public.paymenttype(payment_id);


-- Completed on 2020-09-27 12:30:21

--
-- PostgreSQL database dump complete
--

