--
-- Base de données: `spring_mvc_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `CITY_ID` int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `CITY_NAME` varchar(10) DEFAULT NULL
);

--
-- Contenu de la table `city`
--

INSERT INTO `city` (`CITY_ID`, `CITY_NAME`) VALUES
(1, 'Tunis'),
(2, 'Sousse'),
(3, 'Monastir'),
(4, 'Msaken'),
(5, 'Sfax'),
(6, 'Bizerte');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `FIRST_NAME` varchar(10) NOT NULL,
  `LAST_NAME` varchar(10) NOT NULL,
  `GENDER` varchar(10) NOT NULL,
  `CITY_ID` int(10) NOT NULL,
  FOREIGN KEY (CITY_ID) REFERENCES CITY(CITY_ID)
);

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`USER_ID`, `FIRST_NAME`, `LAST_NAME`, `GENDER`, `CITY_ID`) VALUES
(1, 'Wahid', 'Gazzah', 'Male', 4),
(2, 'Sadok', 'Rouis', 'Male', 4),
(3, 'Wael', 'Boukadida', 'Male', 1),
(4, 'Amin', 'Bergiga', 'Male', 3),
(5, 'Hammed', 'Majouri', 'Male', 1),
(6, 'Oussama', 'Boudhri', 'Male', 3),
(7, 'Khadija', 'Ben Salah', 'Female', 1),
(8, 'Zakariya', 'Abd Allah', 'Male', 5);

