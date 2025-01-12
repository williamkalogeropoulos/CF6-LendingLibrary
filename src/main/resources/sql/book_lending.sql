-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: book_lending
-- ------------------------------------------------------
-- Server version	8.0.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_author` (`author`),
  FULLTEXT KEY `title` (`title`,`author`),
  FULLTEXT KEY `title_2` (`title`,`author`)
) ENGINE=InnoDB AUTO_INCREMENT=5355 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (207,'Les Misérables','Victor Hugo','9780451419439',1),(208,'Ulysses','James Joyce','9780679722762',1),(209,'Brave New World','Aldous Huxley','9780060850524',1),(210,'The Alchemist','Paulo Coelho','9780062315007',1),(211,'The Hunger Games','Suzanne Collins','9780439023481',1),(212,'The Iliad','Homer','9780140275360',1),(215,'Wuthering Heights','Emily Brontë','9780141439556',1),(216,'Crime and Punishment','Fyodor Dostoevsky','9780486415871',1),(217,'Pride and Prejudice','Jane Austen','9781503290563',1),(219,'To Kill a Mockingbird','Harper Lee','9780061120084',1),(221,'War and Peace','Leo Tolstoy','9780199232765',1),(222,'Anna Karenina','Leo Tolstoy','9780143035008',1),(224,'Sapiens: A Brief History of Humankind','Yuval Noah Harari','9780062316097',1),(226,'Don Quixote','Miguel de Cervantes','9780060934347',1),(228,'The Catcher in the Rye','J.D. Salinger','9780316769488',1),(229,'The Divine Comedy','Dante Alighieri','9780142437223',1),(230,'Madame Bovary','Gustave Flaubert','9780140449129',1),(232,'The Lord of the Rings','J.R.R. Tolkien','9780618640157',1),(234,'The Picture of Dorian Gray','Oscar Wilde','9780141439570',1),(237,'1984','George Orwell','9780451524935',1),(238,'The Odyssey','Homer','9780140268867',1),(241,'The Great Gatsby','F. Scott Fitzgerald','9780743273565',1),(246,'Moby-Dick','Herman Melville','9781503280786',1),(250,'Dracula','Bram Stoker','9780486411095',1),(263,'The Brothers Karamazov','Fyodor Dostoevsky','9780374528379',1),(269,'Frankenstein','Mary Shelley','9780486282114',1),(302,'Jane Eyre','Charlotte Brontë','9780141441146',1),(305,'Harry Potter and the Sorcerer’s Stone','J.K. Rowling','9780590353427',1),(322,'The Count of Monte Cristo','Alexandre Dumas','9780140449266',1),(468,'The Hobbit','J.R.R. Tolkien','9780618968633',1),(5207,'Dune','Frank Herbert','9780441013593',1),(5208,'The Shining','Stephen King','9780307743657',1),(5209,'The Road','Cormac McCarthy','9780307387899',1),(5210,'Beloved','Toni Morrison','9781400033416',1),(5211,'The Sun Also Rises','Ernest Hemingway','9780743297332',1),(5212,'Invisible Man','Ralph Ellison','9780679732761',1),(5213,'Lolita','Vladimir Nabokov','9780679723165',1),(5214,'Catch-22','Joseph Heller','9781451626650',1),(5215,'The Grapes of Wrath','John Steinbeck','9780143039433',1),(5216,'Slaughterhouse-Five','Kurt Vonnegut','9780385333849',1),(5217,'Middlesex','Jeffrey Eugenides','9780312427733',1),(5218,'The Kite Runner','Khaled Hosseini','9781594631931',1),(5219,'A Thousand Splendid Suns','Khaled Hosseini','9781594483851',1),(5220,'The Night Circus','Erin Morgenstern','9780307744432',1),(5221,'The Name of the Wind','Patrick Rothfuss','9780756404741',1),(5222,'The Lies of Locke Lamora','Scott Lynch','9780553588941',1),(5223,'A Game of Thrones','George R.R. Martin','9780553103549',1),(5224,'Red Rising','Pierce Brown','9780345539786',1),(5225,'The Blade Itself','Joe Abercrombie','9780316387317',1),(5226,'Hyperion','Dan Simmons','9780553283685',1),(5227,'The Left Hand of Darkness','Ursula K. Le Guin','9780441478125',1),(5228,'Foundation','Isaac Asimov','9780553293356',1),(5229,'Neuromancer','William Gibson','9780441569595',1),(5230,'Snow Crash','Neal Stephenson','9780553380957',1),(5231,'The Three-Body Problem','Liu Cixin','9780765382030',1),(5232,'The Wind-Up Bird Chronicle','Haruki Murakami','9780679775430',1),(5233,'Norwegian Wood','Haruki Murakami','9780375704024',1),(5234,'Kafka on the Shore','Haruki Murakami','9781400079278',1),(5235,'Cloud Atlas','David Mitchell','9780375507250',1),(5236,'The House of the Spirits','Isabel Allende','9781501117015',1),(5237,'One Day','David Nicholls','9780307946713',1),(5238,'Me Before You','Jojo Moyes','9780143124542',1),(5239,'Big Little Lies','Liane Moriarty','9780425274866',1),(5240,'The Help','Kathryn Stockett','9780425232200',1),(5241,'Where the Crawdads Sing','Delia Owens','9780735219090',1),(5242,'Before We Were Strangers','Renée Carlino','9781501105777',1),(5243,'The Light We Lost','Jill Santopolo','9780735212756',1),(5244,'It Ends with Us','Colleen Hoover','9781501110368',1),(5245,'Verity','Colleen Hoover','9781791392796',1),(5246,'The Silent Patient','Alex Michaelides','9781250301697',1),(5247,'The Girl on the Train','Paula Hawkins','9781594634024',1),(5248,'Gone Girl','Gillian Flynn','9780307588371',1),(5249,'Sharp Objects','Gillian Flynn','9780307341556',1),(5250,'Dark Places','Gillian Flynn','9780307341570',1),(5251,'Behind Closed Doors','B.A. Paris','9781250132369',1),(5252,'The Woman in the Window','A.J. Finn','9780062678416',1),(5253,'The Couple Next Door','Shari Lapena','9780735221109',1),(5254,'The Family Upstairs','Lisa Jewell','9781501190117',1),(5255,'The Turn of the Key','Ruth Ware','9781501188770',1),(5256,'Lock Every Door','Riley Sager','9781524745141',1),(5257,'The Goldfinch','Donna Tartt','9780316055444',1),(5258,'The Shadow of the Wind','Carlos Ruiz Zafón','9780143034902',1),(5259,'A Man Called Ove','Fredrik Backman','9781476738024',1),(5260,'The Night Watch','Sergei Lukyanenko','9781843430385',1),(5261,'The 5th Wave','Rick Yancey','9780399162411',1),(5262,'Red Queen','Victoria Aveyard','9780062310644',1),(5263,'The Tattooist of Auschwitz','Heather Morris','9780062797155',1),(5264,'Eleanor Oliphant Is Completely Fine','Gail Honeyman','9780735220683',1),(5265,'Anxious People','Fredrik Backman','9781501160837',1),(5266,'Project Hail Mary','Andy Weir','9780593135204',1),(5267,'The Midnight Library','Matt Haig','9780525559474',1),(5268,'Klara and the Sun','Kazuo Ishiguro','9780593318171',1),(5269,'A Discovery of Witches','Deborah Harkness','9780670022410',1),(5270,'Shadow and Bone','Leigh Bardugo','9781250027436',1),(5271,'The Giver of Stars','Jojo Moyes','9780399562488',1),(5272,'The Paris Library','Janet Skeslien Charles','9781982134198',1),(5273,'Mexican Gothic','Silvia Moreno-Garcia','9780525620785',1),(5274,'The Book Thief','Markus Zusak','9780375842208',1),(5275,'The Nightingale','Kristin Hannah','9781250080400',1),(5276,'The Light Between Oceans','M.L. Stedman','9781451681758',1),(5277,'The Inheritance Games','Jennifer Lynn Barnes','9781368052405',1),(5278,'We Were Liars','E. Lockhart','9780385741262',1),(5279,'They Both Die at the End','Adam Silvera','9780062457805',1),(5280,'All the Light We Cannot See','Anthony Doerr','9781501173219',1),(5281,'The Storied Life of A.J. Fikry','Gabrielle Zevin','9781616204518',1),(5282,'Circe','Madeline Miller','9780316556347',1),(5283,'The House in the Cerulean Sea','TJ Klune','9781250217318',1),(5284,'Pachinko','Min Jin Lee','9781455563920',1),(5286,'The Vanishing Half','Brit Bennett','9780525536291',1),(5287,'Malibu Rising','Taylor Jenkins Reid','9781524798659',1),(5288,'Daisy Jones & The Six','Taylor Jenkins Reid','9781524798628',1),(5289,'The Seven Husbands of Evelyn Hugo','Taylor Jenkins Reid','9781501161933',1),(5291,'It Starts with Us','Colleen Hoover','9781668001226',1),(5292,'The Love Hypothesis','Ali Hazelwood','9780593336823',1),(5293,'Love and Other Words','Christina Lauren','9781501128011',1),(5294,'Book Lovers','Emily Henry','9780593440872',1),(5295,'Beach Read','Emily Henry','9781984806734',1),(5296,'People We Meet on Vacation','Emily Henry','9781984806758',1),(5297,'Reminders of Him','Colleen Hoover','9781542025607',1),(5298,'Ugly Love','Colleen Hoover','9781476753188',1),(5299,'The Paper Palace','Miranda Cowley Heller','9780593329826',1),(5300,'The Ex Hex','Erin Sterling','9780063027473',1),(5301,'The Wife Between Us','Greer Hendricks & Sarah Pekkanen','9781250130945',1),(5302,'The Chain','Adrian McKinty','9780316531238',1),(5303,'Behind Her Eyes','Sarah Pinborough','9781250151346',1),(5304,'The Perfect Nanny','Leila Slimani','9780143132172',1),(5305,'An American Marriage','Tayari Jones','9781616208684',1),(5306,'The Book of Lost Names','Kristin Harmel','9781982131890',1),(5307,'The City We Became','N.K. Jemisin','9780316509848',1),(5309,'The Overstory','Richard Powers','9780393356687',1),(5310,'American Dirt','Jeanine Cummins','9781250209764',1),(5311,'The Alice Network','Kate Quinn','9780062654195',1),(5312,'The Huntress','Kate Quinn','9780062740379',1),(5314,'The Other Black Girl','Zakiya Dalila Harris','9781982160135',1),(5315,'Shantaram','Gregory David Roberts','9780312330521',1),(5316,'Beneath a Scarlet Sky','Mark Sullivan','9781503943377',1),(5317,'The Rosie Project','Graeme Simsion','9781476729091',1),(5318,'Tomorrow, and Tomorrow, and Tomorrow','Gabrielle Zevin','9780593321201',1),(5319,'Cloud Cuckoo Land','Anthony Doerr','9781982168438',1),(5320,'Lessons in Chemistry','Bonnie Garmus','9780385547345',1),(5321,'Small Great Things','Jodi Picoult','9780345544971',1),(5322,'Wish You Were Here','Jodi Picoult','9781984818416',1),(5323,'Apples Never Fall','Liane Moriarty','9781250220257',1),(5324,'The Last House on Needless Street','Catriona Ward','9781250812629',1),(5325,'The It Girl','Ruth Ware','9781982155261',1),(5326,'Wrong Place Wrong Time','Gillian McAllister','9780063252345',1),(5327,'Things We Never Got Over','Lucy Score','9781945631832',1),(5328,'Every Summer After','Carley Fortune','9780593438534',1),(5329,'Forever, Interrupted','Taylor Jenkins Reid','9781476712826',1),(5330,'Maybe in Another Life','Taylor Jenkins Reid','9781476776880',1),(5331,'After I Do','Taylor Jenkins Reid','9781476712840',1),(5332,'The One','John Marrs','9781335005106',1),(5333,'The Institute','Stephen King','9781982110567',1),(5334,'The Outsider','Stephen King','9781501180989',1),(5335,'Doctor Sleep','Stephen King','9781451698855',1),(5336,'Fairy Tale','Stephen King','9781668002179',1),(5337,'The 100-Year-Old Man Who Climbed Out the Window and Disappeared','Jonas Jonasson','9781401324644',1),(5338,'Beartown','Fredrik Backman','9781501160769',1),(5339,'Us Against You','Fredrik Backman','9781501160806',1),(5340,'The Winners','Fredrik Backman','9781982112799',1),(5341,'The Good Sister','Sally Hepworth','9781250120977',1),(5342,'The Mother-in-Law','Sally Hepworth','9781250120922',1),(5344,'The Last Time I Lied','Riley Sager','9781524743079',1),(5345,'Final Girls','Riley Sager','9781101985366',1),(5346,'Home Before Dark','Riley Sager','9781524745189',1),(5347,'Survive the Night','Riley Sager','9780593183168',1),(5348,'The Girl Before','JP Delaney','9780425285046',1),(5349,'Watching You','Lisa Jewell','9781501190070',1),(5350,'Then She Was Gone','Lisa Jewell','9781501154652',1),(5351,'The Night Watchman','Louise Erdrich','9780062671185',1),(5352,'The Sentence','Louise Erdrich','9780062671130',1),(5353,'Evvie Drake Starts Over','Linda Holmes','9780525619246',1),(5354,'The Lightkeeper\'s Daughters','Jean E. Pendziwol','9780062572024',1);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowings`
--

DROP TABLE IF EXISTS `borrowings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `borrow_date` date NOT NULL,
  `overdue_charges` decimal(38,2) NOT NULL,
  `return_date` date DEFAULT NULL,
  `book_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6q1qai7on9rcryji44lyglqt1` (`book_id`),
  KEY `FKaexiaowfdka601ns4qv7pu0re` (`user_id`),
  CONSTRAINT `FK6q1qai7on9rcryji44lyglqt1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `FKaexiaowfdka601ns4qv7pu0re` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowings`
--

LOCK TABLES `borrowings` WRITE;
/*!40000 ALTER TABLE `borrowings` DISABLE KEYS */;
INSERT INTO `borrowings` VALUES (2,'2025-01-12',0.00,'2025-01-12',207,3),(3,'2025-01-12',0.00,'2025-01-12',211,3),(4,'2025-01-12',0.00,'2025-01-12',207,3);
/*!40000 ALTER TABLE `borrowings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$dshaYlsJWe9X.7V/Mg3L0uWPEGDP1.PWIGIwHgE1st4pLZ063dGsi','ADMIN',''),(3,'user','$2a$10$KAVislIoNRQw4FneWG91GeD6im8upTUDWhxDUVWCuvOYGA.dDGLnK','USER',''),(7,'user2','$2a$10$N.AP5zdJcTYg5bd6hUm/x.QMLEClSfWLQFRYZPRutZDJ6aSqV3OUi','USER','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-12 23:48:32
