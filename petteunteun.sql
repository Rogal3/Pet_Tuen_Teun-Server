-- --------------------------------------------------------
-- 호스트:                          kjasmx.myds.me
-- 서버 버전:                        10.3.11-MariaDB - Source distribution
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- petteunteun 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `petteunteun` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `petteunteun`;

-- 테이블 petteunteun.animal 구조 내보내기
CREATE TABLE IF NOT EXISTS `animal` (
  `id` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `owner` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `age` int(11) NOT NULL DEFAULT 0,
  `species` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `adopt` char(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1970-01-01',
  `birth` char(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `animalOwner` (`owner`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.animal:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` (`id`, `owner`, `name`, `age`, `species`, `adopt`, `birth`) VALUES
	('jr29jr#슬기', 'jr29jr', '슬기', 0, '', '', ''),
	('jr29jr#준이', 'jr29jr', '준이', 0, '', '', ''),
	('rogal3#바니', 'rogal3', '바니', 9, '말티즈', '', ''),
	('sungyil#요크', 'sungyil', '요크', 3, '요크셔테리어', '1970-01-01', '');
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;

-- 테이블 petteunteun.comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `comment` (
  `id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `writer` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `post` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `writeTime` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1970-01-01 00:00:00',
  PRIMARY KEY (`id`),
  KEY `commentWriter` (`writer`),
  KEY `commentPost` (`post`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.comment:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`id`, `writer`, `post`, `content`, `writeTime`) VALUES
	('p000001#0', 'jr29jr', 'p000001', '그건 맞지', '1970-01-01 00:00:00'),
	('p000001#1', 'rogal3', 'p000001', '고럼고럼', '1970-01-01 00:00:00');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;

-- 테이블 petteunteun.doctor 구조 내보내기
CREATE TABLE IF NOT EXISTS `doctor` (
  `name` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `hospital` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `position` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `major` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`name`),
  KEY `doctorHospital` (`hospital`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.doctor:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` (`name`, `hospital`, `position`, `major`) VALUES
	('박박사', '태능동물병원', '원장', '정신학과'),
	('오박사님', '튼튼병원', '원장', '수의학과');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;

-- 테이블 petteunteun.hospital 구조 내보내기
CREATE TABLE IF NOT EXISTS `hospital` (
  `name` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `address` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `phoneNum` char(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `openTime` char(8) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '00:00:00',
  `closeTime` char(8) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.hospital:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT INTO `hospital` (`name`, `address`, `phoneNum`, `openTime`, `closeTime`) VALUES
	('태능동물병원', '서울특별시 중랑구 동일로 920 3층', '029731953', '00:00:00', '121212'),
	('튼튼병원', '시골짜기', '010', '09:00', '17:00');
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;

-- 테이블 petteunteun.lastid 구조 내보내기
CREATE TABLE IF NOT EXISTS `lastid` (
  `name` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `lastId` bigint(20) unsigned NOT NULL DEFAULT 100,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.lastid:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `lastid` DISABLE KEYS */;
INSERT INTO `lastid` (`name`, `lastId`) VALUES
	('post', 100);
/*!40000 ALTER TABLE `lastid` ENABLE KEYS */;

-- 테이블 petteunteun.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `name` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `address` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `phoneNum` char(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '01000000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.member:~6 rows (대략적) 내보내기
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `password`, `name`, `address`, `phoneNum`) VALUES
	('jr29jr', '1234', '이은호', '대한민국', '010'),
	('koka', '1234', '김영훈', '쪽본', '01000000000'),
	('komo6521', '1234', '튼튼병원', '일산', '01000000000'),
	('rogal3', '1234', '최우석', '대한민국', '010'),
	('sungyil', '1234', '박성일', '대한민국', '010'),
	('vip', '1234', '태능동물병원', '서울특별시 중랑구 동일로 920 3층', '0297319535');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- 테이블 petteunteun.post 구조 내보내기
CREATE TABLE IF NOT EXISTS `post` (
  `id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `type` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `writer` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `writeTime` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1970-01-01 00:00:00',
  PRIMARY KEY (`id`),
  KEY `postWriter` (`writer`),
  KEY `postType` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.post:~15 rows (대략적) 내보내기
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` (`id`, `type`, `title`, `writer`, `content`, `writeTime`) VALUES
	('p000001', '자유게시판', '나는 빡빡이다.', 'sungyil', '다들 수긍하시는지?', '1970-01-01 00:00:00'),
	('p000002', '자유게시판', '우리개 이쁘져', 'jr29jr', '폰이 고장나서 사진은 업서요~~', '1970-01-01 00:00:00'),
	('p000003', '자유게시판', '아무데나 똥싸서 어쩌죠', 'koka', '제가 힘조절을 못해서 ㅠㅠ', '1970-01-01 00:00:00'),
	('p000004', '자유게시판', '말티즈 먹이는 보통 뭐 쓰시나요??', 'jr29jr', '걍 롤이나 한판 하쉴?', '1970-01-01 00:00:00'),
	('p000005', '자유게시판', '앉아! 일어나! 빵!', 'sungyil', '은호가 말을 참 안듣네요 ^^;;', '1970-01-01 00:00:00'),
	('p000006', '자유게시판', '산책 나갈땐 꼭 목줄을 합시다.', 'jr29jr', '저는 산책은 안나가기로 했씁니다', '1970-01-01 00:00:00'),
	('p000007', '자유게시판', '성일이는 보아라', 'komo6521', '넌 차를 샀으니 셔틀이다. 이 셔틀아', '1970-01-01 00:00:00'),
	('p000008', '자유게시판', '게시글은 최대 몇자까지될까?게시글은 최대 몇자까지될까?게시글은 최대 몇자까지될까?게시글은 최대 몇자까지될까?게시글은 최대 몇자까지될까?', 'rogal3', '내용은 2만자까지 된대 얘들아', '1970-01-01 00:00:00'),
	('p000009', '자유게시판', '9번째 게시글', 'rogal3', '아홉수', '1970-01-01 00:00:00'),
	('p000010', '자유게시판', '공지사항', 'rogal3', '공지사항은 이 윗글에 있습니다.', '1970-01-01 00:00:00'),
	('p000011', '자유게시판', '2차 공지사항', 'rogal3', '2차 공지사항은 이 아랫글에 있습니다.', '1970-01-01 00:00:00'),
	('p000012', '자유게시판', '할 게임 추천해주세요 협동게임으루다가', 'rogal3', '할겜이 없어요 몬헌 아이스본 은제나오냐 목빠지것다', '1970-01-01 00:00:00'),
	('p000013', '자유게시판', '병원은 역시 가던데로 가야해요', 'jr29jr', '문도! 가고 싶은 데로 간다!', '1970-01-01 00:00:00'),
	('p000014', '자유게시판', '코레와 난도 수레도데스까?', 'koka', '한구거 까먹었다데스', '1970-01-01 00:00:00'),
	('p000015', '자유게시판', '이 전쟁을 끝내러왔다.', 'komo6521', '왼팔은 다음 세대에 전해주고왔다.', '1970-01-01 00:00:00');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;

-- 테이블 petteunteun.rating 구조 내보내기
CREATE TABLE IF NOT EXISTS `rating` (
  `id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `writer` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `hospital` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `scale` int(11) NOT NULL DEFAULT 0,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `ratingWriter` (`writer`),
  KEY `ratingHospital` (`hospital`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.rating:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` (`id`, `writer`, `hospital`, `scale`, `content`) VALUES
	('rogal3#튼튼병원', 'rogal3', '튼튼병원', 0, '서비스로 콜라가 안왔어요.');
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;

-- 테이블 petteunteun.reservation 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `hospitalMemberID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `customerID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `reservationType` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `reservationTime` char(19) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1970-01-01 00:00:00',
  `isExecuted` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `reservationWriter` (`hospitalMemberID`),
  KEY `reservationHospital` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 테이블 데이터 petteunteun.reservation:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` (`id`, `hospitalMemberID`, `customerID`, `reservationType`, `reservationTime`, `isExecuted`) VALUES
	('r001', 'komo6521', 'jr29jr', '우리 은호가 어디 아픈가봐요.', '1970-01-01 00:00:00', 0),
	('r002', 'komo6521', 'sungyil', '아픔. 걍 존내아픔.', '1970-01-01 00:00:00', 0);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
