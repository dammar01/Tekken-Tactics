-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 09 Jan 2025 pada 14.24
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tekken_tactics`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `character`
--

CREATE TABLE `character` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `difficulty` enum('Easy','Medium','Hard') DEFAULT NULL,
  `evasiveness` int(11) DEFAULT NULL,
  `mobility` int(11) DEFAULT NULL,
  `throw_game` int(11) DEFAULT NULL,
  `combo_damage` int(11) DEFAULT NULL,
  `wall_carry` int(11) DEFAULT NULL,
  `tier` enum('S','A','B','C','D','-') NOT NULL DEFAULT '-',
  `last_updated` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `character`
--

INSERT INTO `character` (`id`, `name`, `code`, `difficulty`, `evasiveness`, `mobility`, `throw_game`, `combo_damage`, `wall_carry`, `tier`, `last_updated`) VALUES
(0, 'Kuma', 'kuma', 'Medium', 4, 3, 5, 6, 5, 'D', '2024-12-22 11:08:52'),
(1, 'Alisa', 'alisa', 'Easy', 6, 8, 5, 6, 6, 'B', '2024-12-22 11:20:04'),
(2, 'Asuka', 'asuka', 'Easy', 7, 5, 5, 5, 4, 'B', '2024-12-16 13:57:20'),
(3, 'Azucena ', 'azucena', 'Medium', 9, 6, 5, 7, 7, 'S', '2024-12-16 13:57:20'),
(4, 'Bryan', 'bryan', 'Hard', 5, 5, 5, 9, 5, 'B', '2024-12-16 13:57:20'),
(5, 'Claudio', 'claudio', 'Easy', 5, 6, 5, 6, 6, 'B', '2024-12-16 13:57:20'),
(6, 'Devil Jin', 'dvj', 'Hard', 7, 7, 5, 10, 5, 'S', '2024-12-16 13:57:20'),
(7, 'Dragunov', 'dragunov', 'Medium', 5, 6, 8, 8, 9, 'S', '2024-12-16 13:57:20'),
(8, 'Eddy', 'eddy', 'Medium', 7, 5, 6, 8, 8, '-', '2024-12-16 13:57:20'),
(9, 'Feng', 'feng', 'Medium', 7, 6, 5, 5, 8, 'S', '2024-12-16 13:57:20'),
(10, 'Heihachi', 'heihachi', 'Hard', 5, 7, 5, 6, 3, '-', '2024-12-16 13:57:20'),
(11, 'Hwoarang', 'hwoarang', 'Hard', 5, 7, 5, 7, 7, 'B', '2024-12-16 13:57:20'),
(12, 'Jack-8', 'jack_8', 'Medium', 3, 4, 6, 6, 5, 'C', '2024-12-16 13:57:20'),
(13, 'Jin', 'jin', 'Hard', 5, 7, 5, 7, 6, '-', '2024-12-16 13:57:20'),
(14, 'Jun', 'jun', 'Medium', 6, 5, 5, 8, 6, 'S', '2024-12-16 13:57:20'),
(15, 'Kazuya', 'kazuya', 'Hard', 5, 7, 6, 6, 6, 'B', '2024-12-16 13:57:20'),
(16, 'King', 'king', 'Hard', 6, 6, 10, 6, 6, 'A', '2024-12-16 13:57:20'),
(18, 'Lars', 'lars', 'Medium', 6, 7, 6, 7, 9, 'B', '2024-12-16 13:57:20'),
(19, 'Law', 'law', 'Medium', 5, 5, 5, 6, 6, 'B', '2024-12-16 13:57:20'),
(20, 'Lee', 'lee', 'Hard', 5, 5, 5, 6, 10, 'S', '2024-12-22 11:17:51'),
(21, 'Leo', 'leo', 'Medium', 6, 6, 5, 5, 6, 'B', '2024-12-16 13:57:20'),
(22, 'Leroy', 'leroy', 'Easy', 8, 5, 5, 7, 7, 'B', '2024-12-16 13:57:20'),
(23, 'Lidia', 'lidia', 'Hard', 6, 5, 5, 6, 7, 'B', '2024-12-16 13:57:20'),
(24, 'Lili', 'lili', 'Easy', 6, 7, 7, 6, 6, 'A', '2024-12-16 13:57:20'),
(25, 'Nina', 'nina', 'Hard', 6, 6, 10, 5, 7, 'A', '2024-12-16 13:57:20'),
(26, 'Panda', 'panda', 'Easy', 4, 3, 5, 6, 5, 'D', '2024-12-16 13:57:20'),
(27, 'Paul', 'paul', 'Medium', 6, 6, 7, 10, 6, 'B', '2024-12-16 13:57:20'),
(28, 'Raven', 'raven', 'Medium', 7, 6, 5, 8, 8, 'B', '2024-12-16 13:57:20'),
(29, 'Reina', 'reina', 'Hard', 7, 7, 5, 7, 6, 'S', '2024-12-16 13:57:20'),
(30, 'Shaheen', 'shaheen', 'Easy', 5, 5, 5, 5, 6, 'B', '2024-12-16 13:57:20'),
(31, 'Steve', 'steve', 'Hard', 6, 7, 5, 8, 6, 'C', '2024-12-16 13:57:20'),
(32, 'Victor', 'victor', 'Medium', 8, 8, 5, 6, 6, 'A', '2024-12-16 13:57:20'),
(33, 'Xiaoyu', 'xiaoyu', 'Hard', 10, 7, 5, 9, 5, 'S', '2024-12-16 13:57:20'),
(34, 'Yoshimitsu', 'yoshimitsu', 'Hard', 8, 7, 6, 4, 7, 'A', '2024-12-16 13:57:20'),
(35, 'Zafina', 'zafina', 'Medium', 9, 7, 5, 5, 6, 'C', '2024-12-16 13:57:20');

-- --------------------------------------------------------

--
-- Struktur dari tabel `combo_list`
--

CREATE TABLE `combo_list` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `character_id` int(11) DEFAULT NULL,
  `notation` varchar(255) DEFAULT NULL,
  `name_move` varchar(255) DEFAULT 'General Combo',
  `version` varchar(255) DEFAULT NULL,
  `total_damage` varchar(255) DEFAULT NULL,
  `total_hits` varchar(255) DEFAULT NULL,
  `date_created` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `combo_list`
--

INSERT INTO `combo_list` (`id`, `user_id`, `character_id`, `notation`, `name_move`, `version`, `total_damage`, `total_hits`, `date_created`) VALUES
(1, 1, 19, '[df,4,3,dash,4,u,3,\'next\',b,2,\'next\',b,2,\'next\',b,2,^h,i^w^s,3,^f,3,^f,\'next\',f,f,3,df,4,3,dash,4,u,3,\'next\',b,2,\'next\',b,2,\'next\',b,2,^h,i^w^s,3,^f,3,^f,\'next\',f,f,3,df,4,3,dash,4,u,3,\'next\',b,2,\'next\',b,2,\'next\',b,2,^h,i^w^s,3,^f,3,^f,\'next\',f,f,3,df,4,', 'General Combo', '1.09', '96', '11', '2024-12-20 21:23:31'),
(3, 1, 19, '[uf,4,3,^s^s^l,3,b,2,3,4,^f,f,4]', 'General Combo', '1.05', '79', '7', '2024-12-20 21:23:31'),
(4, 1, 19, '[^h,2,4,u,3,\'next\',dash,4,3,^f,1+2,dash,\'next\',4,3,^f,3,^f,\'next\',dash,\'next\',4,3,^f,4]', 'General Combo', '-', '89', '12', '2024-12-20 21:23:31');

-- --------------------------------------------------------

--
-- Struktur dari tabel `faq`
--

CREATE TABLE `faq` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `faq`
--

INSERT INTO `faq` (`id`, `title`, `content`) VALUES
(1, 'Judul', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'),
(2, 'Judul', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'),
(3, 'Judul', 'tes update');

-- --------------------------------------------------------

--
-- Struktur dari tabel `favorite_combo`
--

CREATE TABLE `favorite_combo` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `combo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `favorite_combo`
--

INSERT INTO `favorite_combo` (`id`, `user_id`, `combo_id`) VALUES
(4, -1, 3),
(6, -1, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `help`
--

CREATE TABLE `help` (
  `id` int(11) NOT NULL,
  `tipe` enum('About','My Account','Guide','Register And Login') DEFAULT NULL,
  `content` text DEFAULT NULL,
  `line` int(11) DEFAULT NULL,
  `is_new_line` tinyint(1) DEFAULT NULL,
  `is_title` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `help`
--

INSERT INTO `help` (`id`, `tipe`, `content`, `line`, `is_new_line`, `is_title`) VALUES
(2, 'About', 'We are a team of four individuals who have an interest and passion in app and game development. Our team members are:', 2, 1, 0),
(3, 'About', '1. Dammar Syaputra', 3, 0, 0),
(4, 'About', '2. Haikal Bagas Putra', 4, 0, 0),
(5, 'About', '3. Muhammad Haris', 5, 0, 0),
(6, 'About', '4. Andika', 6, 1, 0),
(7, 'About', 'Our project, Tekken Tactic\'s, is an app dedicated to Tekken 8 gamers. This application is designed to help players understand and master various characters', 7, 0, 0),
(8, 'About', 'in Tekken 8, ranging from character ranks, moves, to attack combinations (combos). With Tekken Tactic\'s, users can find the right combo for their favorite', 8, 0, 0),
(9, 'About', 'characters, as well as create unique combos according to their playstyle. ', 9, 1, 0),
(10, 'About', 'Our Vision We are committed to providing the best experience for Tekken 8 players, by providing complete and easily accessible information about the', 10, 0, 0),
(11, 'About', 'characters and strategies in the game.', 11, 1, 0),
(12, 'About', 'Key Features', 12, 0, 1),
(13, 'About', '-       Character Guide :Detail information about the character, including unique moves and abilities.', 13, 0, 0),
(14, 'About', '-       Combo List: The best combo recommendations that players can use.', 14, 0, 0),
(15, 'About', '-       My Combo: A feature for users to save and design combos according to their needs.', 15, 1, 0),
(16, 'About', 'We hope that Tekken Tactic\'s can be a useful guide and improve your gaming experience in the world of Tekken 8.', 16, 0, 0),
(17, 'My Account', 'What is Username? ', 1, 0, 1),
(18, 'My Account', 'Username is a name that is used to identify yourself on a website, computer, app, etc.', 2, 0, 0),
(19, 'My Account', 'Username is necessary for you to login into “Tekken’s Tactic” and as your display name.', 3, 0, 0),
(20, 'My Account', 'Here you can set your Username into whatever you like, but you can’t use symbols except for “-” and “_”.', 4, 1, 0),
(21, 'My Account', 'What is Password? ', 5, 0, 1),
(22, 'My Account', 'Password is a secret key for accessing the user\'s account.', 6, 0, 0),
(23, 'My Account', 'Password is necessary for you to login into “Tekken’s Tactic” and important to remember.', 7, 0, 0),
(24, 'My Account', 'Here you can set your Password into whatever you like, but your password is must meet the complexity', 8, 0, 0),
(25, 'My Account', 'restrisction such as minimum password length of 8 and maximum password length of 25. Password must', 9, 0, 0),
(26, 'My Account', 'contains letters, numbers, symbols, and special characters.', 10, 1, 0),
(27, 'My Account', 'How do I set or change my Username or Password? ', 11, 0, 1),
(28, 'My Account', 'To set or change your Username or Password, you can start by go to the “My Account” page (you can go there', 12, 0, 0),
(29, 'My Account', 'with click here).', 13, 1, 0),
(30, 'My Account', 'After you click the link, you will get sent to “My Account” page (example on picture on the left). To set or change ', 14, 0, 0),
(31, 'My Account', 'your Username or Password, you can follow the arrow on picture on the left then press the button “Edit Profile”. ', 15, 0, 0),
(32, 'My Account', 'If you already click the button, you will get sent to page where you can change your Username or Password', 16, 0, 0),
(33, 'My Account', '(example on picture on the right).', 17, 0, 0),
(34, 'Guide', 'How is Guide help us?', 1, 0, 1),
(35, 'Guide', 'Guide can help Tekken 8’s player to know more about each characters in this game.', 2, 0, 0),
(36, 'Guide', 'Information that can help Tekken 8’s player is such as character’s tier list, character’s abilities, and character’s', 3, 0, 0),
(37, 'Guide', 'move sheet.', 4, 1, 0),
(38, 'Guide', 'How do I use Guide to help me?', 5, 0, 1),
(39, 'Guide', 'To use Guide as your helper, first thing you can do is get to the “Guide” page by click here.', 6, 1, 0),
(40, 'Guide', 'After you click the link, you will get sent to “Guide” page (example on picture on the left). As you can see there’s', 7, 0, 0),
(41, 'Guide', 'characters from Tekken 8. The characters is being categorized with tier list so you will know what character’s in', 8, 0, 0),
(42, 'Guide', 'meta now. To look up for more detail to a specific character, you can press on any characters there and you will', 9, 0, 0),
(43, 'Guide', 'get sent to another page for the specific character that you chose (example on picture on the right). On that', 10, 0, 0),
(44, 'Guide', 'page, you will see the character’s ability and character’s move sheet that will help you to know more about the', 11, 0, 0),
(45, 'Guide', 'character.', 12, 0, 0),
(46, 'Register And Login', 'How do I register or login here?', 1, 0, 1),
(47, 'Register And Login', 'When you open this app, you will landing on Login page (example on picture on the bottom of this paragraph). If', 2, 0, 0),
(48, 'Register And Login', 'you already have an account, you can continue to submit your username and password to login on this app.', 3, 0, 0),
(49, 'Register And Login', 'However, if you don’t have an account yet, you can register with pressing the link named “Register here” that ', 4, 0, 0),
(50, 'Register And Login', 'exist on Login page (you can go to the Login page by click here).', 5, 1, 0),
(51, 'Register And Login', 'For the case if you don’t have an account, after you click the link, you will get sent to “Register” page (example ', 6, 0, 0),
(52, 'Register And Login', 'on picture on the bottom of this paragraph). To register and get an account, you need to submit an existing ', 7, 0, 0),
(53, 'Register And Login', 'email, username, and password. Username and Password must pass all the requirements that already being set', 8, 0, 0),
(54, 'Register And Login', '(you can check the requirements detail by click here. After the registration succeed, you can go back to the', 9, 0, 0),
(55, 'Register And Login', 'Login page by clicking the link named “Login here”. There you can submit your username and password that you', 10, 0, 0),
(56, 'Register And Login', 'already made.', 11, 1, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `movesheet`
--

CREATE TABLE `movesheet` (
  `id` int(11) NOT NULL,
  `character_id` int(11) DEFAULT NULL,
  `notation` varchar(255) DEFAULT NULL,
  `name_move` varchar(255) DEFAULT NULL,
  `damage` varchar(255) DEFAULT NULL,
  `frame_startup` varchar(255) DEFAULT NULL,
  `hit_properties` enum('High','Mid','Low') DEFAULT NULL,
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `movesheet`
--

INSERT INTO `movesheet` (`id`, `character_id`, `notation`, `name_move`, `damage`, `frame_startup`, `hit_properties`, `notes`) VALUES
(1, 1, '[\"2+3\"]', 'Heat Burst', '[12;12]', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b\r\n3 chip damage on block\r\n12 chip damage on hit\r\nOnly deals recoverable damage\r\nCannot cause a K.O.'),
(2, 1, '[\"^h\",\"2+3\"]', 'Quick-fix Protocol', '50 (28)', 'i20~21', 'Mid', 'Heat Smash\r\nTransition to SBT with 3_4\r\nTransition to DBT with f+3_f+4\r\n14 chip damage on block'),
(3, 1, '[\"1+2\"]', 'Uninstall', '22', 'i19~20', 'Mid', 'Heat Engager\r\nHeat Dash +67a (+50) on hit, +5 on block\r\nBalcony Break\r\nSpike\r\nHead\r\n6 chip damage on block'),
(4, 1, '[\"d\",\"3\"]', 'Quick Slider', '17', 'i23~32', 'Low', 'BKP transition is -18~-10'),
(5, 1, '[\"db\",\"1\"]', 'Backup', '10', 'i18~19', 'Mid', 'Transition to FC with D\r\nCan perform WS moves after 24 frames'),
(6, 1, '[\"db\",\"2\"]', 'Daisy Chain', '10', 'i19~20', 'Mid', 'Combo from 1st hit with 12f delay\r\nInput can be delayed 15f\r\nMove can be delayed 14f\r\nEnter DES with 1+2'),
(7, 1, '[\"db\",\"3\"]', 'Deep Link', '11', 'i16', 'Low', '-'),
(8, 1, '[\"db\",\"4\"]', 'Deep Web', '23', 'i24~25', 'Low', '-'),
(9, 1, '[\"1+2\"]', 'Gadget Found', '30', 'i25~40 i26~28', 'Mid', 'Tornado\r\nHead\r\nInput can be delayed 13f\r\n2nd hit combos into 3rd on CH only'),
(10, 1, '[\"b\",\"2\",\"1\"]', 'Trance Hammer', '17', 'i25~26', 'Mid', 'Spike\r\nCombo from 1st hit with 7f delay\r\nInput can be delayed 12'),
(11, 1, '[\"b\",\"2\",\"3\"]', 'Trance Generator', '22', 'i23~24', 'Mid', 'Tornado\r\nBalcony Break\r\nCombo from 1st hit with 9f delay\r\nInput can be delayed 12f'),
(12, 2, '[\"2+3\"]', 'Heat Burst', '[12;12]', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b\r\n3 chip damage on block\r\n12 chip damage on hit\r\nOnly deals recoverable damage\r\nCannot cause a K.O.'),
(13, 2, '[\"^h\",\"2+3\"]', 'Triple Ascension Kicks', '12,​11,​27', 'i16', 'Mid', 'Heat Smash\r\nFloor Break\r\n17 chip damage on block\r\nTransition to attack throw on hit\r\n+15a if only the last hit connects\r\n+11d (-24) if only the last two hits connect\r\n2nd hit damage 11 > 18 if 1st hit whiffs\r\n3rd hit damage 27 > 30 if 1st hit whiffs\r\nHit level becomes m,m,m on block/whiff'),
(14, 2, '[\"f\",\"1+2\"]', 'Exorcisor', '26', 'i15~16', 'High', 'Heat Engager\r\nHeat Dash +43a (+35), +5\r\nBalcony Break\r\n+0 on block after absorbing an attack\r\nChip damage on block after attack absorption\r\nGain Naniwa Gusto on Heat activation'),
(15, 2, '[\"d\",\"2\"]', 'Heaven\'s Hammer', '18', 'i18', 'Mid', 'Heat Engager\r\nHeat Dash +67a (+50), +5 on block\r\nSpike\r\nGain NWG on Heat activation'),
(16, 2, '[\"db\",\"1\",\"2\"]', 'Swallow Vortex', '20', 'i26~27', 'High', 'Heat Engager\r\nHeat Dash +34a (+27), +5\r\nBalcony Break\r\nCombo from 1st hit with 8f delay\r\nGain NWG on Heat activation'),
(17, 2, '[\"^s^s\",\"2\"]', 'Mist Palm Thrust', '23', 'i13~14', 'Mid', 'Heat Engager\r\nHeat Dash +36a (+26), +5\r\nGain NWG on Heat activation\r\nBalcony break on airborne hit'),
(19, 2, '[\"1\",\"​2\",\"3\"]', 'Jab Uppercut > Spinning Heel Drop', '24', 'i31~33', 'Mid', 'Balcony Break\r\nChip damage on block\r\nCancel to r18 with B\r\nInterrupt with i15 from 2nd block\r\nLast input can be held to power up'),
(20, 2, '[\"df\",\"1\",\"2\"]', 'Falling Tower', '13', 'i22', 'High', 'Floor Break\r\nCombo from 1st CH with 2f delay\r\nCombo from 1st hit when off-axis to the left or right\r\nInput can be delayed 7f\r\n+18a on off-axis hit, +16a on BT hit\r\nTransition to attack throw on standing front hit\r\nWon\'t transition to throw if opponent is too far (Jack-8 range 3.3~), gives +13 on hit instead'),
(21, 2, '[\"df\",\"1\",\"4\"]', 'Kyara Boku', '22', 'i23~24', 'Mid', 'Balcony Break\r\nCombo from 1st CH with 1f delay\r\nInput can be delayed 7f'),
(22, 3, '[\"2+3\"]', 'Heat Burst', '12;12', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b\r\n3 chip damage on block\r\n12 chip damage on hit\r\nOnly deals recoverable damage\r\nCannot cause a K.O.\r\n'),
(23, 3, '[\"^h\",\"2+3\"]', 'Azucena Speciality', '15,​9,​29', 'i15~16', 'Mid', 'Heat Smash\r\nReversal Break\r\nBalcony Break\r\n17 chip damage on block\r\nTransition to attack throw on hit\r\nEnter LIB +6 automatically on block/whiff\r\n+15a if only the last hit connects\r\n+30a (+20) if only the last two hits connect\r\n2nd hit damage 9 > 15 if 1st hit whiffs\r\n3rd hit damage 29 > 30 if 1st hit whiffs\r\nHit level becomes m,h,m on block/whiff\r\nSpike on the last hit if the first two whiff'),
(24, 3, '[\"^h\",\"2+3+4\"]', 'Coffee Break', '0', 'i45', '', 'Parries all high or low attacks\r\nCancel to r20 with DB'),
(25, 3, '[\"^h\",\"b\",\"1\",\"1\",\"2\",\"F\"]', 'Double Blend Tempestad > Nuevo Libertador', '_', '_', NULL, 'Strong Aerial Tailspin\r\nChip damage on block'),
(29, 3, '[\"f\",\"1+2\"]', 'Aero Press Pegar', '25', 'i16~17', 'High', 'Heat Engager\r\nHeat Dash +43a (+35), +5\r\nBalcony Break\r\n+0 on block if an attack is absorbed'),
(30, 3, '[b, 3, 4, df, ^w^bl!, ^w^bl!, ub]', 'Tac√≥n Plunger', '25', 'i16~17', 'High', 'Heat Engager\r\nHeat Dash +67a (+50), +5\r\nSpike\r\nChip damage on block'),
(31, 3, '[\"df\",\"2\"]', 'Mount Rainier', '12', 'i15~16\r\n', 'Mid', 'Launches crouching opponent'),
(32, 4, '[\"2+3\"]', 'Heat Burst', '12;12', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b\r\n3 chip damage on block\r\n12 chip damage on hit\r\nOnly deals recoverable damage\r\nCannot cause a K.O.'),
(33, 4, '[\"^h\",\"2+3\"]', 'Notorious Monster', '20,​20,​35', 'i16', 'Mid', 'Heat Smash\r\nReversal Break\r\nSpike\r\nTransition to attack throw on h'),
(34, 4, '[\"1\",\"​2\",\"4\"]', 'One Two Neck Breaker', '24', 'i20', 'High', 'Heat Engager\r\nHeat Dash +34a +5g\r\nBalcony Break\r\nCombo from 1st CH\r\nCombo from 2nd CH with 3f delay\r\nMove can be delayed 2f'),
(35, 4, '[\"db\",\"1+2\"]', 'Crash Impact', '16', 'i15 i12', 'High', 'Heat Engager\r\nHeat Dash +43a (+35), +5\r\nBalcony break on airborne hit\r\nJail from 1st attack'),
(36, 4, '[\"^f\",\"2\"]', 'Mach Breaker', '29', 'i13', 'High', 'Heat Engager\r\nHeat Dash +36a (+26), +5\r\nBalcony Break\r\nChip damage on block (8)\r\nSometimes written Mach Punch'),
(38, 4, '[\"^s^s\",\"2\",\"1\"]', 'Snake Fang', '20', 'i21~22', 'High', 'Heat Engager\r\nHeat Dash +62a (+42), +5\r\nBalcony Break\r\nElbow\r\nCombo from 1st hit with 8f delay\r\nInput can be delayed 11f\r\nMove can be delayed 10f'),
(39, 4, '[\"1\"]', 'Jab', '5', 'i10', 'High', 'Recovers 2f faster on hit or block (t27 r17)'),
(40, 4, '[\"1\",\"2\"]', 'Left Right Combo', '8', '', 'High', 'Jail from 1st attack'),
(41, 4, '[\"1\"]', 'One Two Body Blow', '18', 'i19', 'High', 'Combo from 2nd CH with 10f delay'),
(42, 5, '[\"2+3\"]', 'Heat Burst', '12;12', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b\r\n3 chip damage on block\r\n12 chip damage on hit\r\nOnly deals recoverable damage\r\nCannot cause a K.O.'),
(43, 5, '[\"^h\",\"2+3\"]', 'Stella Cadente', '40', 'i18~19', 'Mid', 'Heat Smash\r\nReversal Break\r\nBalcony Break\r\nTransition to attack throw on hit\r\nChip damage on block'),
(44, 5, '[\"2\",\"​1\",\"2\"]', 'Circle of Judgement', '25', 'i16~18', 'Mid', 'Heat Engager\r\nHeat Dash +62a (+42), +5\r\nStrong Aerial Tailspin\r\nBalcony Break\r\nCombo from 2nd CH with 14f delay\r\nInput can be delayed 15f\r\nMove can be delayed 10f\r\nTransition to r65 STB on hit without STB\r\n-10a (-1) upon STB gain on an airborne hit\r\nInterrupt with i1 from 1st block'),
(45, 5, '[\"3\",\"2\"]', 'Requiescat', '22', 'i22~23', 'High', 'Heat Engager\r\nHeat Dash +34a (+27), +5\r\nBalcony Break\r\nTransition to r55 STB on hit without STB\r\n-6a (-13) upon STB gain on an airborne hit'),
(46, 5, '[\"b\",\"4\",\"2\"]', 'Shining Ray', '20', 'i20', 'Mid', 'Heat Engager\r\nHeat Dash +36a (+26), +5\r\nBalcony Break\r\nCombo from 1st hit with no delay\r\nCombo from 1st CH with 10f delay\r\nInput can be delayed 12F\r\nTransition to r45 STB on hit without STB and Heat'),
(47, 5, '[\"u\",\"2\"]', 'Meteor Storm', '21', 'i21~22', 'Mid', 'Heat Engager\r\nHeat Dash +67a (+50), +5\r\nBalcony Break\r\nSpike\r\nTransition to r49 STB on hit without STB and Heat'),
(48, 5, '[\"f\",\"f\",\"^f\",\"2\"]', 'Ira', '40', 'i13~16', 'Mid', 'Heat Engager\r\nHeat Dash +36a (+26)\r\nBalcony Break\r\nTransition to r51 STB without STB and Heat\r\nFrame advantage +1a (-4) on STB gain\r\nChip damage on block\r\nCannot be buffered\r\nSometimes written wr2'),
(49, 5, '[\"1\"]', 'Jab', '5', 'i10', 'High', 'Recovers 2f faster on hit or block (t27 r17)'),
(50, 5, '[\"2\"]', 'Scattered Ashes', '12', 'i10', 'High', 'Jail from 1st attack with 5f delay\r\nInput can be delayed 5f\r\nMove can be delayed 4f'),
(51, 5, '[\"3\"]', 'Absolve', '11', 'i22', 'Low', 'Combo from 1st CH with 3f delay\r\nInput can be delayed 5f\r\nMove can be delayed 4f'),
(52, 6, '[\"2+3\"]', 'Heat Burst', '12;12', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b\r\n3 chip damage on block\r\n12 chip damage on hit\r\nOnly deals recoverable damage\r\nCannot cause a K.O.'),
(53, 6, '[\"^h\",\"2+3\"]', 'Vindictive Helix', '0,​22,​10\r\n4.0', 'i18', 'High', 'Heat Smash\r\nJail from 1st attack\r\nTransition to attack throw on hit\r\nTransition to MCR on block only\r\nCan cancel transition to MCR on block with B at +2~+12\r\n8 Chip Damage on block'),
(55, 6, '[\"^h\",\"1+2\"]', 'Wheel of Pain > Annihilation Beam', '15', 'ii24-35', 'Mid', 'Tornado\r\nWeapon\r\n4 Chip Damage on block\r\nConsumes 220F of remaining heat time'),
(56, 6, '[\"^h\",\"1+2\"]', 'Hisou > Annihilation Beam', '20', 'i25~50', 'Mid', '6 Chip Damage on block\nAvailable only as combo from 1st hit\nUnavailable on whiff\nTransition to attack throw on hit\nConsumes 250F of remaining heat time'),
(57, 6, '[\"^h\",\"1+2\"]', 'Laser Crush > Annihilation Beam', '15', 'i24~35', 'Mid', 'Balcony Break\r\nTornado\r\nWeapon\r\n4 Chip Damage on block\r\nConsumes 220F of remaining heat time'),
(58, 6, '[\"^h\",\"1+2\"]', 'Spiral of Madness > Annihilation Beam', '20', 'i25~36', 'Mid', 'Tornado\r\nWeapon\r\n6 Chip Damage on block\r\nConsumes 220F of remaining heat time'),
(59, 22, '[\"3\"]', 'Rakshasa\'s Fury', '23', 'i25~127', 'Mid', 'Heat Engager\r\nHeat Dash +62a (+42) on hit, +5 on block\r\nBalcony Break\r\n16 Chip Damage on Heat Dash\r\nCombo from 1st hit with 5f delay\r\nMove can be delayed 3f'),
(60, 6, '[\"b\",\"3\"]', 'Wicked Jambu Spear', '24', 'i29~32', 'Mid', 'Heat Engager\r\nHeat Dash +62a (+42) on hit, +5 on block\r\nBalcony Break\r\n16 Chip Damage on Heat Dash'),
(61, 6, '[\"f\",\"^f\",\"2\"]', 'Demon\'s Paw', '24', 'i15~16', 'Mid', 'Heat Engager\r\nHeat Dash +36a (+26) on hit, +5 on block\r\nBalcony Break\r\ni18 startup when unbuffered, and i16 startup when buffered\r\n7 Chip Damage on block\r\n10 Chip Damage on block while in heat\r\n20 Chip Damage on Heat Dash'),
(63, 7, '[\"^h\",\"2+3\"]', 'Ultra Russian Sickle', '23', 'i15~16', 'Mid', 'Heat Smash\r\nBalcony Break\r\nOnly 1st and 3rd hit\r\nChip damage on block\r\nThrow on 1st hit - 50 damag'),
(64, 7, '[\"^h\",\"2+3\",\"db\",\"1+2\"]', 'Ultra Russian Sickle > Ambush Tackle', '23', 'i26', 'Mid', 'Heat Smash'),
(65, 7, '[\"^f\",\"2\",\"^h\",\"1+2\"]', 'Russian Hook Special > Ambush Tackle', '22', '', 'Mid', 'Partially uses remaining Heat time'),
(66, 7, '[\"^h\",\"1+2\"]', 'Hail Slicer > Ambush Tackle\r\n', '8', 'i40', 'Low', 'Partially uses remaining Heat time'),
(67, 7, '[\"1\"]', 'Salvo', '20', 'i19~20', 'Mid', 'Heat Engager\r\nHeat Dash +5, +43a (+35)\r\nBalcony Break\r\nCombo from 1st or 2nd CH\r\nOpponent recovers Crouching'),
(68, 7, '[\"1+2\"]', 'Crushing Blizzard', '27', 'i30~31', 'Mid', 'Heat Engager\r\nHeat Dash +5, +67a (+50)\r\nBalcony Break\r\nSpike\r\nCombo from 1st hit\r\nChip damage on block'),
(69, 7, '[\"3\"]', 'Tundra Slash', '16', 'i17~18', 'High', 'Heat Engager\r\nHeat Dash +5, +62a (+42)\r\nBalcony Break\r\nCombo from 1st hit with 1f delay'),
(71, 7, '[\"d\",\"1+3\"]', 'Cold Fate', '30', 'i12~14', 'Mid', 'Heat Engager\r\nCrouch throw\r\nAlternate input 2+4'),
(82, 9, '[\"2+3\"]', 'Heat Burst', '12;12', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b\r\n3 chip damage on block\r\n12 chip damage on hit\r\nOnly deals recoverable damage\r\nCannot cause a K.O.'),
(83, 9, '[\"^h\",\"2+3\"]', 'Yinglong Mountain Crusher', '26', 'i21,24', 'Mid', 'Heat Smash\r\nChip damage on block\r\nTransitions to STC on block, can cancel with b.'),
(84, 9, '[\"^h\",\"3+4\"]', 'Jiaolong Sea Splitter', '30', 'i30~31', 'Mid', 'Partially consumes remaining Heat\r\nCreates an unblockable shockwave along the ground that is +7g on hit and forces the opponent to crouch. The shockwave comes out even if the kick itself whiffs, or is parried by certain parries like Jin\'s or Reina\'s. It can be absorbed by a Power Crush.'),
(85, 9, '[\"f\",\"1+2\"]', 'Tiger\'s Claw', '25', 'i19~20\r\n', 'High', 'Heat Engager\r\nHeat Dash +5, +35d\r\nBalcony Break'),
(86, 9, '[\"b\",\"1+2\"]', 'Iron Fortress', '31', 'i13~14', 'Mid', 'Heat Engager\r\nHeat Dash +5 +18 at wall, +43d (+35)\r\nBalcony Break'),
(87, 9, '[\"f\",\"^f\",\"2\"]', 'Exploding Dagger', '25', 'i18~19', 'Mid', 'Heat Engager\r\nHeat Dash +5, +36a (+26)\r\nBalcony Break'),
(90, 9, '[\"^h\",\"b\",\"1\"]\n\n', 'Essence of Iron Palm', '20', 'i10~11', 'High', 'Balcony Break\r\nPartially uses remaining Heat Time'),
(91, 9, '[\"^h\",\"f\",\"^f\",\"1+2\"]', 'Essence of Nian Zhang Mie Ba', '30', 'i39', 'Mid', 'Balcony Break\r\nGuard Break\r\nPartially uses remaining Heat Time'),
(92, 10, '[\"2+3\"]', 'Heat Burst', '12;12', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b\r\n3 chip damage on block\r\n12 chip damage on hit\r\nOnly deals recoverable damage\r\nCannot cause a K.O.'),
(93, 10, '[\"^h\",\"2+3\"]', 'Enraged Tettsui Otoshi', '50', 'i15', 'Mid', 'Heat Smash\r\nReversal Break\r\nFloor Break\r\nChip damage on block\r\nTransition to attack throw on hit\r\nTransition to RAI on block only\r\nCancel RAI transition with B, +6 on block instead'),
(94, 10, '[\"^h\",\"2+3\",\"^f\"]', 'Eight Thunder Kami Slayer', '50', 'i15', 'Mid', 'Heat Smash\r\nReversal Break\r\nBalcony Break\r\nChip damage on block\r\nTransition to attack throw on hit\r\nTransition to RAI on block only\r\nCancel RAI transition with B, +6 on block instead'),
(95, 10, '[\"^h\",\"db\",\"1+2\"]', 'Thunderous Battering Ram', '25', 'i30', 'Mid', 'Tornado\r\nReversal Break\r\nBalcony Break\r\nChip damage on block\r\nConsumes remaining Heat time'),
(96, 10, '[\"^h\",\"2\"]', 'Rending Battering Ram\r\nRAI', '25', 'i30', 'Mid', 'Tornado\r\nReversal Break\r\nCombo from 1st hit\r\nChip damage on block\r\nConsumes remaining Heat time\r\nSometimes written H.d+3,2'),
(97, 10, '[\"^h\",\"1\"]', 'Soaring Anvil Smasher\r\n', '30', 'i25~30', 'Mid', 'Balcony Break\r\nChip damage on block\r\nConsumes remaining Heat time\r\nCombo from 1st hit\r\nSometimes written H.uf+4,1'),
(98, 10, '[\"^h\",\"b\",\"b\",\"n\",\"3+4\"]', 'Shadow Step', '-', '-', NULL, 'Consumes all of the remaining Heat time\r\nTremendous backwards trave'),
(99, 10, '[\"b\",\"1+2\"]', 'Heaven\'s Merciless Wrath', '20', 'i42', 'Mid', 'Heat Engager\r\nHeat Dash +5, +43d (+35)\r\nBalcony Break\r\nGain 1 Ultimate Count on Heat activation\r\nParries all mid or high punches or kicks\r\nAttack is guaranteed after a successful parry'),
(100, 10, '[\"uf\",\"2\",\"1\"]', 'Kidney Crush Combo', '20', 'i27~28', 'Mid', 'Heat Engager\r\nHeat Dash +5, +36a (+26)\r\nBalcony Break\r\nChip damage on block\r\nCombo from 1st hit with 10f delay\r\nGain 1 Ultimate Count on Heat activation'),
(101, 10, '[\"f\",\"^f\",\"1+2\"]', 'Raoh Thrust', '22', 'i13', 'Mid', 'Heat Engager\r\nHeat Dash +5, +36a (+26)\r\nBalcony Break\r\nChip damage on block\r\nGain 1 Ultimate Count on Heat activation'),
(102, 11, '[\"2+3\"]', 'Heat Burst', '12', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b'),
(103, 11, '[\"^h\",\"2+3\"]', 'Trinity Claymore', '​14', 'i17~18, i18~19, i29', 'Mid', 'Heat Smash\r\nJails\r\nAlso possible during Right Stance and Left Flamingo\r\nShifts to Right Flamingo'),
(104, 10, '[\"df\",\"3+4\"]', 'Ignition Barrage', '17', 'i13,i14,i24~25', 'Mid', 'Heat Engager\r\nJails\r\nDeals chip damage on block\r\nAlso possible during Right Stance'),
(105, 11, '[\"2+3\"]', 'Heat Burst', '12', 'i16', 'Mid', 'Heat Burst\r\nCancel to r45 with b,b'),
(106, 11, '[\"^h\",\"3\"]', 'Trinity Claymore', '13,​14,​24(51)', 'i17~18, i18~19, i29', 'Mid', 'Heat Smash\r\nJails\r\nAlso possible during Right Stance and Left Flamingo\r\nShifts to Right Flamingo'),
(107, 7, '[\"2+3\"]', 'Heat Burst', '12', 'i16', 'Mid', 'Heat Burst\r\n100% recoverable damage\r\nChip damage on block'),
(108, 34, '[1]', 'Update', '2', '10', 'Mid', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `email`, `username`, `password`) VALUES
(-1, 'admin@gmail.com', 'admin', '123'),
(1, 'tes@gmail.com', 'tes', '123'),
(2, 'player2@gmail.com', 'player2', '123123123');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `character`
--
ALTER TABLE `character`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `combo_list`
--
ALTER TABLE `combo_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `character_id` (`character_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `faq`
--
ALTER TABLE `faq`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `favorite_combo`
--
ALTER TABLE `favorite_combo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `combo_id` (`combo_id`);

--
-- Indeks untuk tabel `help`
--
ALTER TABLE `help`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `movesheet`
--
ALTER TABLE `movesheet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `character_id` (`character_id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `character`
--
ALTER TABLE `character`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT untuk tabel `combo_list`
--
ALTER TABLE `combo_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `faq`
--
ALTER TABLE `faq`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `favorite_combo`
--
ALTER TABLE `favorite_combo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `help`
--
ALTER TABLE `help`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT untuk tabel `movesheet`
--
ALTER TABLE `movesheet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `combo_list`
--
ALTER TABLE `combo_list`
  ADD CONSTRAINT `combo_list_ibfk_1` FOREIGN KEY (`character_id`) REFERENCES `character` (`id`),
  ADD CONSTRAINT `combo_list_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ketidakleluasaan untuk tabel `favorite_combo`
--
ALTER TABLE `favorite_combo`
  ADD CONSTRAINT `favorite_combo_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `favorite_combo_ibfk_2` FOREIGN KEY (`combo_id`) REFERENCES `combo_list` (`id`);

--
-- Ketidakleluasaan untuk tabel `movesheet`
--
ALTER TABLE `movesheet`
  ADD CONSTRAINT `movesheet_ibfk_1` FOREIGN KEY (`character_id`) REFERENCES `character` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
