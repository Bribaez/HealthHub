-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2024 a las 22:03:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.3.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `healthhub`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `id` int(11) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`id`, `mail`, `id_usuario`, `fecha`) VALUES
(1, 'bri@gmail.com', 1, '2024-10-09');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `historial_medico` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`id`, `nombre`, `apellido`, `fecha_nacimiento`, `historial_medico`) VALUES
(2, 'Martina', 'López', '1995-06-15', NULL),
(4, 'Lucas', 'Gómez', '1987-11-22', NULL),
(5, 'Sofía', 'Pérez', '1993-03-08', NULL),
(8, 'Julian', 'Ramírez', '2001-07-14', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_medico`
--

CREATE TABLE `registro_medico` (
  `id` int(11) NOT NULL,
  `id_turno` bigint(20) NOT NULL,
  `fecha_registro` date NOT NULL,
  `diagnostico` text NOT NULL,
  `tratamiento` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registro_medico`
--

INSERT INTO `registro_medico` (`id`, `id_turno`, `fecha_registro`, `diagnostico`, `tratamiento`) VALUES
(1, 1, '2024-12-01', 'Dolor en el pecho', 'Descanso y revisión cardiológica'),
(2, 3, '2024-12-02', 'Alergia estacional', 'Antihistamínicos y cuidado nasal'),
(3, 4, '2024-12-03', 'Migraña severa', 'Analgésicos y reposo'),
(4, 9, '2024-12-04', 'Fiebre alta', 'Paracetamol y reposo absoluto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `id` bigint(20) NOT NULL,
  `paciente_id` int(11) NOT NULL,
  `medico_id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estado` varchar(40) NOT NULL,
  `descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`id`, `paciente_id`, `medico_id`, `fecha`, `hora`, `estado`, `descripcion`) VALUES
(1, 2, 3, '2024-12-01', '10:00:00', 'pendiente', 'Consulta general'),
(2, 2, 3, '2024-12-01', '11:00:00', 'confirmado', 'Revisión médica'),
(3, 4, 6, '2024-12-02', '09:00:00', 'pendiente', 'Chequeo anual'),
(4, 5, 6, '2024-12-02', '09:30:00', 'pendiente', 'Consulta por dolor de cabeza'),
(5, 2, 7, '2024-12-02', '10:00:00', 'confirmado', 'Revisión médica'),
(6, 8, 7, '2024-12-02', '10:30:00', 'cancelado', 'Control de presión arterial'),
(7, 4, 3, '2024-12-03', '11:00:00', 'pendiente', 'Control de peso'),
(8, 5, 3, '2024-12-03', '11:30:00', 'confirmado', 'Chequeo post-cirugía'),
(9, 8, 6, '2024-12-04', '12:00:00', 'realizado', 'Consulta pediátrica'),
(10, 2, 7, '2024-12-05', '12:30:00', 'realizado', 'Consulta dermatológica'),
(11, 5, 6, '2024-12-06', '14:00:00', 'pendiente', 'Seguimiento post-tratamiento'),
(12, 4, 7, '2024-12-07', '15:00:00', 'confirmado', 'Consulta nutricional'),
(13, 6, 1, '2024-12-01', '14:00:00', 'pendiente', 'Consulta general');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `rol` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `rol`, `password`) VALUES
(1, 'Brisa', 'admin', '1234'),
(2, 'Martina', 'paciente', '1234'),
(3, 'Rosa', 'medico', '1234'),
(4, 'Lucas', 'paciente', '1234'),
(5, 'Sofia', 'paciente', '1234'),
(6, 'Carlos', 'medico', '1234'),
(7, 'Maria', 'medico', '1234'),
(8, 'Julian', 'paciente', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `registro_medico`
--
ALTER TABLE `registro_medico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_turno` (`id_turno`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `paciente_id` (`paciente_id`),
  ADD KEY `medico_id` (`medico_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `registro_medico`
--
ALTER TABLE `registro_medico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
