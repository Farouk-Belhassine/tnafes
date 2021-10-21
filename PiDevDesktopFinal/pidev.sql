-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 26 avr. 2021 à 12:37
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonnement`
--

DROP TABLE IF EXISTS `abonnement`;
CREATE TABLE IF NOT EXISTS `abonnement` (
  `id_abonnement` int(11) NOT NULL AUTO_INCREMENT,
  `id_utilisateur` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_abonnement`),
  KEY `fk_idclientab` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `abonnement`
--

INSERT INTO `abonnement` (`id_abonnement`, `id_utilisateur`, `date_debut`, `date_fin`, `type`) VALUES
(193, 91, '2016-01-01', '2016-01-01', 'Abonnement annuel'),
(194, 98, '2016-01-01', '2016-01-01', 'Abonnement annuel'),
(195, 98, '2016-01-01', '2016-01-01', 'Abonnnement mensuel'),
(196, 1, '2016-01-01', '2017-01-01', 'Abonnement annuel');

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

DROP TABLE IF EXISTS `activite`;
CREATE TABLE IF NOT EXISTS `activite` (
  `id_activite` int(11) NOT NULL AUTO_INCREMENT,
  `id_categorie` int(11) DEFAULT NULL,
  `id_coachact` int(11) DEFAULT NULL,
  `titre` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `nomcat` varchar(20) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id_activite`),
  KEY `fk_idcat` (`id_categorie`),
  KEY `fk_idcoach_act` (`id_coachact`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`id_activite`, `id_categorie`, `id_coachact`, `titre`, `description`, `nomcat`, `date`) VALUES
(2, NULL, NULL, 'aa', 'aaa', 'aa', '2021-03-31'),
(3, NULL, NULL, 'aa', 'aaa', 'aa', '2021-03-31'),
(4, NULL, NULL, 'aa', 'aaa', 'aa', '2021-03-31'),
(5, NULL, NULL, 'Yoga', 'aaaa', 'yoga', '2021-04-01');

-- --------------------------------------------------------

--
-- Structure de la table `adminstrateur`
--

DROP TABLE IF EXISTS `adminstrateur`;
CREATE TABLE IF NOT EXISTS `adminstrateur` (
  `id_admin` int(11) NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adminstrateur`
--

INSERT INTO `adminstrateur` (`id_admin`) VALUES
(6);

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id_article` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `image` varchar(1000) NOT NULL,
  PRIMARY KEY (`id_article`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

DROP TABLE IF EXISTS `avis`;
CREATE TABLE IF NOT EXISTS `avis` (
  `id_avis` int(11) NOT NULL AUTO_INCREMENT,
  `id_clientavis` int(11) DEFAULT NULL,
  `idpub_rate` int(11) DEFAULT NULL,
  `email` varchar(75) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `date_avis` date NOT NULL,
  PRIMARY KEY (`id_avis`),
  KEY `fk_idclientavis` (`id_clientavis`),
  KEY `fkidpubrate` (`idpub_rate`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `avis`
--

INSERT INTO `avis` (`id_avis`, `id_clientavis`, `idpub_rate`, `email`, `rating`, `date_avis`) VALUES
(1, 36, 53, 'miral.barhoumi@esprit.tn', 5, '2021-04-24');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id_categorie` int(11) NOT NULL AUTO_INCREMENT,
  `nom_categorie` varchar(20) NOT NULL,
  PRIMARY KEY (`id_categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_categorie`, `nom_categorie`) VALUES
(2, 'aa'),
(5, 'yoga');

-- --------------------------------------------------------

--
-- Structure de la table `categorie_event`
--

DROP TABLE IF EXISTS `categorie_event`;
CREATE TABLE IF NOT EXISTS `categorie_event` (
  `id_categ_event` int(11) NOT NULL AUTO_INCREMENT,
  `thematique` varchar(20) NOT NULL,
  PRIMARY KEY (`id_categ_event`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie_event`
--

INSERT INTO `categorie_event` (`id_categ_event`, `thematique`) VALUES
(38, 'errr'),
(39, 'hhok'),
(40, 'eee'),
(41, 'ttt'),
(42, 'jhgkhil'),
(43, 'aaa'),
(44, 'camping'),
(45, 'Randonne');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `permaban` tinyint(1) DEFAULT '0',
  `dateblock` date DEFAULT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `permaban`, `dateblock`) VALUES
(1, 0, NULL),
(2, 0, NULL),
(3, 0, NULL),
(4, 0, NULL),
(5, 0, NULL),
(7, 0, NULL),
(18, 0, NULL),
(19, 0, NULL),
(20, 0, NULL),
(23, 0, NULL),
(24, 0, NULL),
(78, 0, NULL),
(81, 0, NULL),
(91, 0, NULL),
(97, 0, NULL),
(98, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `coach`
--

DROP TABLE IF EXISTS `coach`;
CREATE TABLE IF NOT EXISTS `coach` (
  `id_coach` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) NOT NULL,
  `salaire` int(11) NOT NULL,
  PRIMARY KEY (`id_coach`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `coach`
--

INSERT INTO `coach` (`id_coach`, `role`, `salaire`) VALUES
(74, 'yoga', 1200),
(75, 'lifestyle4', 1004);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `contenu_comment` varchar(30) NOT NULL,
  `idclient_comment` int(11) DEFAULT NULL,
  `nom_client` varchar(20) DEFAULT NULL,
  `prenom_client` varchar(20) DEFAULT NULL,
  `idpub` int(11) NOT NULL,
  `datecomment` date NOT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `fk_idusercomment` (`idclient_comment`),
  KEY `fk_idpub` (`idpub`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_comment`, `contenu_comment`, `idclient_comment`, `nom_client`, `prenom_client`, `idpub`, `datecomment`) VALUES
(24, '**** you', 19, 'Barhoumi', 'Miral', 53, '2021-04-23'),
(46, 'miral', 98, 'Barhoumi', 'Miral', 53, '2021-04-25'),
(47, 'Magnifique', 98, 'Barhoumi', 'Miral', 54, '2021-04-25');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `id_compte` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id_compte`),
  KEY `fk_compte` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
CREATE TABLE IF NOT EXISTS `consultation` (
  `id_consultation` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `id_coach` int(11) NOT NULL,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  PRIMARY KEY (`id_consultation`),
  KEY `fk_idclient_consultation` (`id_client`),
  KEY `fk_idcoach_consultation` (`id_coach`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `consultation`
--

INSERT INTO `consultation` (`id_consultation`, `id_client`, `id_coach`, `date`, `heure`) VALUES
(35, 0, 0, '2021-03-30', '15:03:00'),
(36, 0, 0, '2021-03-30', '15:03:00'),
(37, 0, 0, '2021-03-30', '15:03:00'),
(38, 0, 0, '2021-03-30', '15:03:00'),
(40, 0, 0, '2021-03-30', '15:03:00'),
(41, 0, 9, '2021-03-16', '00:07:00'),
(42, 0, 22, '2021-03-31', '06:13:00'),
(43, 0, 22, '2021-03-04', '00:17:00'),
(44, 23, 9, '2029-03-16', '01:22:00'),
(46, 23, 9, '2021-03-24', '17:50:00'),
(47, 24, 9, '2021-03-04', '01:15:00'),
(49, 24, 22, '2021-03-05', '02:27:00'),
(50, 23, 17, '2021-03-12', '13:56:00'),
(51, 23, 9, '2021-03-20', '13:57:00'),
(52, 23, 9, '2021-03-31', '12:59:00');

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

DROP TABLE IF EXISTS `doctrine_migration_versions`;
CREATE TABLE IF NOT EXISTS `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20210423020622', '2021-04-23 02:10:49', 14284);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `id_coach` int(11) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `nb_place` int(11) DEFAULT NULL,
  `date_event` date DEFAULT NULL,
  `descriptions` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_event`),
  KEY `fk_idcatE` (`category`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id_event`, `id_coach`, `lieu`, `nb_place`, `date_event`, `descriptions`, `category`, `Description`) VALUES
(56, NULL, 'Hamammet', 27, '2021-04-27', 'la vacuité de l\'esprit, des états de conscience modifiés', 43, '60869c465e073730034410.jpg'),
(58, NULL, 'Bizerte', 65, '2021-05-09', 'Cette pratique peut chercher à produire une paix intérieure, ', 41, '607c66ad79422003846732.jpg'),
(59, NULL, 'Sousse', 0, '2021-04-25', 'duioiypipytgpy', 45, '607f687e34df0047300384.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id_message` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `id_coach` int(11) NOT NULL,
  `heure` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `contenu` varchar(50) NOT NULL,
  PRIMARY KEY (`id_message`),
  KEY `fk_idclient_message` (`id_client`),
  KEY `fk_idcoach_message` (`id_coach`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id_message`, `id_client`, `id_coach`, `heure`, `contenu`) VALUES
(1, 0, 0, '2021-03-09 23:56:01', 'bonjour ca va ?'),
(2, 0, 0, '2021-03-09 23:56:26', 'BONJOUR'),
(3, 0, 0, '2021-03-10 00:34:54', 'bonjour'),
(4, 0, 0, '2021-03-11 09:11:00', 'bonjour\n'),
(5, 0, 0, '2021-03-29 15:12:03', 'ABC'),
(6, 0, 0, '2021-03-29 15:13:57', 'uuuuu'),
(7, 0, 0, '2021-03-29 15:15:00', 'aaaa'),
(8, 0, 0, '2021-03-29 15:19:03', 'aaaa'),
(9, 0, 0, '2021-03-29 23:56:08', '3asléma'),
(10, 23, 9, '2021-03-30 00:18:45', 'saleeeeeem'),
(11, 23, 22, '2021-03-30 00:19:02', '3aslmtiiin'),
(12, 23, 9, '2021-03-31 01:35:30', 'aaaaa');

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

DROP TABLE IF EXISTS `offre`;
CREATE TABLE IF NOT EXISTS `offre` (
  `id_offre` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_offre`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `offre`
--

INSERT INTO `offre` (`id_offre`, `email`, `type`) VALUES
(1, 'louaymohamed29@gmail.com', 'offre cours garatuis 2 Mois'),
(3, 'louaymohamed29@gmail.com', 'offre cours garatuis 15 jours'),
(5, 'louayohamed29@gmail.com', 'offre cours garatuis 1 Mois'),
(6, 'louaymohamed29@gmail.com', 'offre cours garatuis 1 Mois'),
(7, 'louaymohamed29@gmail.com', 'offre cours garatuis 15 jours'),
(8, 'louaymohamed29@gmail.com', 'offre cours garatuis 1 Mois'),
(9, 'louaymohamed29@gmail.com', 'offre cours garatuis 15 jours'),
(10, 'louaymohamed29@gmail.com', 'offre cours garatuis 1 Mois'),
(11, 'louaymohamed29@gmail.com', 'offre cours garatuis 15 jours'),
(12, 'louaymohamed29@gmail.com', 'offre cours garatuis 1 Mois'),
(14, 'louaymohamed29@gmail.com', 'offre cours garatuis 1 Mois'),
(15, 'louaymohamed29@gmail.com', 'offre cours garatuis 1 Mois'),
(16, 'louaymohamed29@gmail.com', 'offre cours garatuis 15 jours'),
(17, 'louaymohamed29@gmail.com', 'offre cours garatuis 15 jours'),
(18, 'barhoumimiral88@gmail.com', 'offre cours garatuis 15 jours'),
(19, 'miral.barhoumi@esprit.tn', 'offre cours garatuis 15 jours'),
(20, 'miral.barhoumi@esprit.tn', 'offre cours garatuis 15 jours');

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id_participation` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `e_mail` varchar(255) DEFAULT NULL,
  `nb_participant` int(11) DEFAULT NULL,
  `Date_part` date NOT NULL,
  `id_evenement` int(11) DEFAULT NULL,
  `date_evenement` date NOT NULL,
  PRIMARY KEY (`id_participation`),
  KEY `fk_participation` (`id_client`),
  KEY `fk_event` (`id_evenement`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`id_participation`, `id_client`, `nom`, `prenom`, `e_mail`, `nb_participant`, `Date_part`, `id_evenement`, `date_evenement`) VALUES
(14, NULL, 'mm', 'pp', 'ol', 0, '2021-03-18', NULL, '2018-03-02'),
(19, NULL, 'miral', 'barhoumi', 'miral.barhoumi@esprit.tn', 0, '2021-03-18', NULL, '2018-03-02'),
(20, NULL, 'ben mohemed', 'louay', 'louay@esprit.tn', 0, '2021-03-23', NULL, '2020-04-03'),
(21, NULL, 'youssef', 'bennour', 'bennour@gmail.com', 0, '2021-03-23', NULL, '2021-07-01'),
(22, NULL, 'salim', 'hasni', 'hasni@esprit.tn', 0, '2021-03-27', NULL, '2021-03-18'),
(23, NULL, 'miral', 'barhoumi', 'barhoumi@', 0, '2021-03-27', NULL, '2012-03-03'),
(24, NULL, 'miha', 'taboubi', 'taboubi@', 0, '2021-03-27', NULL, '2020-03-09'),
(25, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-20'),
(26, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-20'),
(27, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-20'),
(28, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-05'),
(29, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-20'),
(30, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-20'),
(31, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-05'),
(32, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-31'),
(33, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-29', NULL, '2021-03-05'),
(34, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-30', NULL, '2021-03-05'),
(35, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-30', NULL, '2021-03-20'),
(36, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-30', NULL, '2021-03-20'),
(37, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-30', NULL, '2021-03-05'),
(39, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-30', NULL, '2021-03-31'),
(40, NULL, 'aed', 'ezd', 'cl', 0, '2021-03-30', NULL, '2010-03-03'),
(41, NULL, 'Barhoumi', 'Miral', 'barhoumimiral88@gmail.com', 0, '2021-03-30', NULL, '2010-03-03'),
(42, NULL, 'Barhoumi', 'Miral', 'barhoumimiral88@gmail.com', 0, '2021-03-31', NULL, '2010-03-03'),
(43, NULL, 'Barhoumi', 'Miral', 'miral.barhoumi@esprit.tn', 0, '2021-04-01', NULL, '2021-03-31'),
(44, NULL, NULL, NULL, NULL, NULL, '2021-04-26', NULL, '2021-04-27'),
(45, 6, 'Barhoumi', 'Miral', 'tnafes.tnafes@gmail.com', NULL, '2021-04-26', 56, '2021-04-27'),
(46, 6, 'Barhoumi', 'Miral', 'tnafes.tnafes@gmail.com', NULL, '2021-04-26', 56, '2021-04-27'),
(47, 6, 'Barhoumi', 'Miral', 'tnafes.tnafes@gmail.com', NULL, '2021-04-26', 56, '2021-04-27'),
(48, 6, 'Barhoumi', 'Miral', 'tnafes.tnafes@gmail.com', NULL, '2021-04-26', 56, '2021-04-27');

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `id_pub` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` varchar(500) NOT NULL,
  `date_publication` date NOT NULL,
  `iduser` int(11) DEFAULT '19',
  `urlimage` varchar(1000) NOT NULL,
  `nbcomment` int(11) DEFAULT '0',
  PRIMARY KEY (`id_pub`),
  KEY `fk_iduserpub` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `publication`
--

INSERT INTO `publication` (`id_pub`, `contenu`, `date_publication`, `iduser`, `urlimage`, `nbcomment`) VALUES
(53, '“Plus vous devenez silencieux, plus vous pouvez entendre.”', '2021-04-24', 19, 'gallery_2.jpg', 2),
(54, '\r\n“C’est incroyable comme la vie devient belle quand on prend un moment pour s’arrêter.”', '2021-04-23', 19, 'gallery_4.jpg', 1),
(57, ' \r\n“S’asseoir. Respirer. Ecouter. 3 étapes essentielles pour un esprit sain.”', '2021-04-23', 19, 'gettyimages-727141025-e1515604260317.jpg', 0),
(63, '\r\n“Le monde parle à ceux qui ont l’esprit tranquille.”', '2021-04-23', 19, 'med.jpg', 0),
(67, '“Rien ne rend mes matinées plus lumineuses que 5 millions de salutations au soleil.”', '2021-04-24', NULL, 'images.jfif', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id_reclamation` int(11) NOT NULL AUTO_INCREMENT,
  `idclient_rec` int(11) DEFAULT NULL,
  `nom_user` varchar(20) DEFAULT NULL,
  `prenom_user` varchar(20) DEFAULT NULL,
  `numtel` varchar(20) DEFAULT NULL,
  `email` varchar(75) DEFAULT NULL,
  `description` varchar(75) NOT NULL,
  `objet` varchar(20) NOT NULL,
  `etat` varchar(20) DEFAULT 'En attente',
  `date` date NOT NULL,
  `date_traitement` date DEFAULT NULL,
  PRIMARY KEY (`id_reclamation`),
  KEY `fk_idclientreclam` (`idclient_rec`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `idclient_rec`, `nom_user`, `prenom_user`, `numtel`, `email`, `description`, `objet`, `etat`, `date`, `date_traitement`) VALUES
(155, 98, 'Barhoumi', 'Miral', '+21627354995', 'barhoumimiral88@gmail.com', 'aaaaaaaaaa', 'aaaaaaaaaaaa', 'En attente', '2021-02-10', NULL),
(156, 98, 'Barhoumi', 'Miral', '+21627354995', 'barhoumimiral88@gmail.com', 'aaaaaaaaaa', 'aaaaaaaaaaaa', 'En attente', '2021-02-10', NULL),
(158, 23, 'Barhoumi', 'Miral', '+21627354995', 'barhoumimiral88@gmail.com', 'aaaaaaaaa', 'aaaaaaaa', 'En traitement', '2021-03-31', NULL),
(159, 32, 'Barhoumi', 'Miral', '+21627354995', 'miral.barhoumi@esprit.tn', 'aaaaaa', 'sujet', 'Traitée', '2021-04-01', '2021-04-25'),
(160, 25, 'Barhoumi', 'Miral', '+21627354995', 'miral.barhoumi@esprit.tn', 'aaaaaaaa', 'aaa', 'Traitée', '2021-04-04', '2021-04-15'),
(161, 25, 'Barhoumi', 'Miral', '+21627354995', 'miral.barhoumi@esprit.tn', 'aaaaaaaa', 'aaa', 'Traitée', '2021-04-04', '2021-04-17'),
(162, 34, 'Barhoumi', 'Oumaima', '+21650528414', 'miral.barhoumi@esprit.tn', 'aaaaa', 'aaaaaaa', 'Traitée', '2021-04-04', '2021-04-14'),
(187, 98, 'Barhoumi', 'Miral', '+21627354995', 'miral.barhoumi@esprit.tn', 'aaa', 'aaa', 'En attente', '2021-04-21', NULL),
(188, 98, 'Barhoumi', 'Miral', '+21627354995', 'miral.barhoumi@esprit.tn', 'aaa', 'aaa', 'En attente', '2021-04-21', NULL),
(189, 98, 'Barhoumi', 'Miral', '+21627354995', 'miral.barhoumi@esprit.tn', 'aaa', 'aaa', 'En attente', '2021-04-21', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `reset_password_request`
--

DROP TABLE IF EXISTS `reset_password_request`;
CREATE TABLE IF NOT EXISTS `reset_password_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `selector` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hashed_token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `requested_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `expires_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  PRIMARY KEY (`id`),
  KEY `IDX_7CE748AA76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roles` json NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(75) NOT NULL,
  `numtel` int(11) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `roles`, `nom`, `prenom`, `email`, `numtel`, `password`) VALUES
(1, '[\"ROLE_CLIENT\"]', 'testajoutnom1', 'testajout1', 'testajoutmail1@mail', 2543, '5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25'),
(2, '[\"ROLE_CLIENT\"]', 'conm', 'copm', 'comm@mm', 0, 'de79217f46138649e82f047e08d1369826b2a8a4fbdb375058e064e1371218e498f9bfbb19e4442a2c59ac91a89056e2f7ff410d001f674d25f8e71d157f4d5f'),
(3, '[\"ROLE_CLIENT\"]', 'test2', 'test1', 'test1test2@gtrrhr', 68335, '5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25'),
(4, '[\"ROLE_CLIENT\"]', 'testfxmlnom', 'testfxmlprenom', 'testfxmlmail@mail', 252525, '5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25'),
(5, '[\"ROLE_CLIENT\"]', 'testnom', 'testprenom', 'testmail@myadmin.local', 85693412, '5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25'),
(6, '[\"ROLE_ADMIN\"]', 'Barhoumi', 'Miral', 'tnafes.tnafes@gmail.com', 666, '$argon2id$v=19$m=65536,t=4,p=1$WEFTa3c5dzVheDhQcUQ1TQ$nCjATOGQU5fhI1q6+bTkBmkmCfsI/pUjGsXGgF6loGc'),
(7, '[\"ROLE_CLIENT\"]', 'testmodifn', 'testmodifp', 'testmodifm@mail', 2222, '5b722b307fce6c944905d132691d5e4a2214b7fe92b738920eb3fce3a90420a19511c3010a0e7712b054daef5b57bad59ecbd93b3280f210578f547f4aed4d25'),
(18, '[\"ROLE_CLIENT\"]', 'tghn', 'tghnju', 'tghy@mt', 3265, '580bf9d1a8a8afe15384aea7c55ea975ba210e8ccdfc2fb5981e83a85744fbec3fc4d8280325b597acd4de01128d618e020d154ae400d362165c3eacecec1a96'),
(19, '[\"ROLE_CLIENT\"]', 'signupn', 'signupp', 'signupm@m', 17585, '64ffc1938a6ebc76bba12094373b5ca7d987386ea7c027e926fdf7690c143f231799ea13c58791b5032d1d106b239bb26b470a98110a50bf0530f1dbc079002'),
(20, '[\"ROLE_CLIENT\"]', 'clientn', 'clientp', 'clientm@m', 2563, '4939d01598c0206c4fe0bf785aedf93caac81e74dc0e72be0451336e2384a4997893432f856cf12076ad704daa20f4678b1c4fc5560290c63399c47076d47d47'),
(23, '[\"ROLE_CLIENT\"]', 'aed', 'ezd', 'cl', 12, 'd281f7f92cf5d69e2ab60ca21d604aa2345af00509b1d4f3e5163519661602fec3b09468798996869d8dfe30256ee83e84e5fd77da74b5c546dcdd731668181f'),
(24, '[\"ROLE_CLIENT\"]', 'youssef', 'bannour', 'youssef.bannour@esprit.tn', 1424, '1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75'),
(74, '[\"ROLE_COACH\"]', 'coachn', 'coachp', 'coachm@yahoo.fr', 5368, 'test'),
(75, '[\"ROLE_COACH\"]', 'klai2', 'bbj2', 'klaibbj@gmail.com', 0, 'test'),
(78, '[\"ROLE_CLIENT\"]', 'testfrontn', 'testfrontp', 'eege@yahoo.com', 142536, 'test'),
(81, '[\"ROLE_CLIENT\"]', 'testhashn', 'testhashm', 'testhash@yahoo.com', 7485, '$argon2id$v=19$m=65536,t=4,p=1$bVlBR1QzTUtmdlc3SXpLVA$hJiBA2+liOsibrUM9TrSCi9P2lucc3SOBGt5A0dg5SY'),
(91, '[\"ROLE_CLIENT\"]', 'ameni', 'rommene', 'ameni.rommene@esprit.tn', 142536, '$argon2id$v=19$m=65536,t=4,p=1$dml2cUZucXZ3Q3pzeFJwVQ$Cdlo+eLVbNSetnDRkLPQyKahqNzh26FNCrG9gy6kkjA'),
(97, '[\"ROLE_CLIENT\"]', 'fr', 'bl', 'faroukbelhassine@yahoo.fr', 96325, '$argon2id$v=19$m=65536,t=4,p=1$RVVqdDBoSTVPcDRjMUZxOA$rW8VXxkJvRuIsyPCufmLyOaRW6YtgcDy4pJ3siiKWzY'),
(98, '[\"ROLE_CLIENT\"]', 'Barhoumi', 'Miral', 'miral.barhoumi@esprit.tn', 27354995, '$argon2id$v=19$m=65536,t=4,p=1$YnR6VmFScFhlSG9RTncyVw$cwy+5vk25WeNSU0dvN0A1ScKS2xUu3OMLbQye1LH9Eo');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `fk_idclientab` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `FK_B8755515A5668A70` FOREIGN KEY (`id_coachact`) REFERENCES `coach` (`id_coach`),
  ADD CONSTRAINT `FK_B8755515C9486A13` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`);

--
-- Contraintes pour la table `adminstrateur`
--
ALTER TABLE `adminstrateur`
  ADD CONSTRAINT `FK_4B148F42668B4C46` FOREIGN KEY (`id_admin`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `FK_8F91ABF040D4A179` FOREIGN KEY (`id_clientavis`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK_8F91ABF0DC20EF16` FOREIGN KEY (`idpub_rate`) REFERENCES `publication` (`id_pub`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `fk_idclient` FOREIGN KEY (`id_client`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `coach`
--
ALTER TABLE `coach`
  ADD CONSTRAINT `fk_idcoach` FOREIGN KEY (`id_coach`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `fk_idpub` FOREIGN KEY (`idpub`) REFERENCES `publication` (`id_pub`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idusercomment` FOREIGN KEY (`idclient_comment`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `fk_compte` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `fk_event` FOREIGN KEY (`id_evenement`) REFERENCES `event` (`id_event`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_participation` FOREIGN KEY (`id_client`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `publication`
--
ALTER TABLE `publication`
  ADD CONSTRAINT `fk_iduserpub` FOREIGN KEY (`iduser`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fk_idclientreclam` FOREIGN KEY (`idclient_rec`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD CONSTRAINT `FK_7CE748AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
