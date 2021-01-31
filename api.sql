-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2021 at 09:43 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `api`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `BookingID` int(11) NOT NULL,
  `BDateFrom` varchar(70) NOT NULL,
  `UserID` int(11) NOT NULL,
  `HotelID` int(11) NOT NULL,
  `NoOfGuest` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`BookingID`, `BDateFrom`, `UserID`, `HotelID`, `NoOfGuest`) VALUES
(14, '2021-01-17 14:52:12', 106, 1004, 1),
(15, '2021-01-20 14:05:55', 0, 1004, 1),
(16, '2021-01-20 14:06:29', 0, 1006, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hotels`
--

CREATE TABLE `hotels` (
  `HotelID` int(11) NOT NULL,
  `HotelName` varchar(255) NOT NULL,
  `HotelLoc` varchar(255) DEFAULT NULL,
  `NoRooms` int(11) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `DateFrom` date DEFAULT NULL,
  `DateTo` date DEFAULT NULL,
  `wifi` tinyint(1) DEFAULT NULL,
  `Breakfast` tinyint(1) DEFAULT NULL,
  `ACNonAC` varchar(1) DEFAULT NULL,
  `RoomAvailability` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotels`
--

INSERT INTO `hotels` (`HotelID`, `HotelName`, `HotelLoc`, `NoRooms`, `Price`, `DateFrom`, `DateTo`, `wifi`, `Breakfast`, `ACNonAC`, `RoomAvailability`) VALUES
(1002, 'Taj', 'Delhi', 5, 30, '2020-12-15', '2020-12-20', 0, 0, 'A', 'Yes'),
(1003, 'Hyatt Regency ', 'Amritsar', 5, 4000, '2021-01-15', '2021-01-22', 1, 1, '0', 'Yes'),
(1004, 'WelcomHotel ', 'Amritsar', 3, 1200, '2021-01-11', '2021-01-31', 1, 1, '1', 'Yes'),
(1005, 'Hotel Cama', 'Mohali', 5, 1200, '2021-01-01', '2021-02-28', 0, 1, '1', 'Yes'),
(1006, 'Roseate House ', 'New Delhi', 6, 1200, '2021-01-01', '2021-03-31', 1, 1, '1', 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `FullName` varchar(255) NOT NULL,
  `Age` int(11) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  `Phone` varchar(255) NOT NULL,
  `Adress` varchar(255) DEFAULT NULL,
  `Pwd` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `FullName`, `Age`, `Email`, `Phone`, `Adress`, `Pwd`) VALUES
(1, 'sai kumar', 22, 'sai@gmail.com', '+3378993335', 'Cite Universite Paris', '12345'),
(104, 'pp', 12, '882@gmail.com', '44t63426', 'dfsh', '1234'),
(105, 'Ashi', 12, 'hz@gmail.com', '+917807370197', 'asfsdaf', 'ash'),
(106, 'praveen', 20, 'prav@gmail.com', '+337899333', 'test', '12345'),
(107, 'soliyana', 32, 'soli@gmail', '+337899555', 'max paris', '12345');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`BookingID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `BookingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
