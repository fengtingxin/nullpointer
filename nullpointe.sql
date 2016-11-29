-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: nullpointer
-- ------------------------------------------------------
-- Server version	5.6.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advice`
--

DROP TABLE IF EXISTS `advice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advice` (
  `adviceId` int(11) NOT NULL AUTO_INCREMENT,
  `adviceUserName` varchar(40) DEFAULT NULL,
  `adviceTheme` varchar(40) DEFAULT NULL,
  `adviceContent` longtext,
  `adviceUserEmail` varchar(40) DEFAULT NULL,
  `adviceTime` datetime DEFAULT NULL,
  PRIMARY KEY (`adviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advice`
--

LOCK TABLES `advice` WRITE;
/*!40000 ALTER TABLE `advice` DISABLE KEYS */;
INSERT INTO `advice` VALUES (1,'烧鸡','123123','123123','1523056@qq.com','2016-11-25 21:36:38'),(2,'烧鸡','123123','123123','1523056@qq.com','2016-11-27 17:27:08'),(3,'烧鸡','123123','2312312sadfasdf','123','2016-11-27 17:29:38'),(4,'123456','2312','123','123123','2016-11-27 17:46:38'),(5,'烧鸡','123123','123123','1523056@qq.com','2016-11-27 21:37:36');
/*!40000 ALTER TABLE `advice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `answerId` int(11) NOT NULL AUTO_INCREMENT,
  `answerContent` longtext,
  `answerAuthorId` int(11) DEFAULT NULL,
  `answerPublishTime` date DEFAULT NULL,
  `answerLikeNum` int(11) DEFAULT '0',
  `answerHateNum` int(11) DEFAULT '0',
  `questionId` int(11) DEFAULT NULL,
  `answerParentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`answerId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'sdffsadfdsa',2,'2016-11-29',NULL,NULL,3,NULL);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bug`
--

DROP TABLE IF EXISTS `bug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bug` (
  `bugId` int(11) NOT NULL AUTO_INCREMENT,
  `bugTitle` varchar(200) DEFAULT NULL,
  `bugDescribe` varchar(200) DEFAULT NULL,
  `bugReason` varchar(400) DEFAULT NULL,
  `bugMethod` longtext,
  `bugPublishTime` date DEFAULT NULL,
  `bugAuthorId` int(11) DEFAULT NULL,
  `bugLikeNum` int(11) DEFAULT '0',
  `bugHateNum` int(11) DEFAULT '0',
  `bugPageviews` int(11) DEFAULT '0',
  PRIMARY KEY (`bugId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bug`
--

LOCK TABLES `bug` WRITE;
/*!40000 ALTER TABLE `bug` DISABLE KEYS */;
INSERT INTO `bug` VALUES (1,'fatal error C1083: Cannot open include file: \'R…….h\': No such file or directory ','fatal error C1083: Cannot open include file: \'R…….h\': No such file or directory ','不能打开包含文件“R…….h”：没有这样的文件或目录。','不能打开包含文件“R…….h”：没有这样的文件或目录。','2016-11-15',1,23,23,45),(2,'fatal error C1010: unexpected end of file while looking for precompiled header directive','fatal error C1010:','寻找预编译头文件路径时遇到了不该遇到的文件尾。','解决方法：\r\n其一，包含正确路径的#include \"stdafx.h\"；\r\n其二，在*.cpp文件的Setting里面设置，C++选项中的分类 precompiled header，选择不包含头文件即可。','2016-11-15',1,1,12,21),(3,'prog4.cpp(8) : error C2440: “初始化”: 无法从“const int”转换为“int &”','prog4.cpp(8) : error C2440: “初始化”: 无法从“const int”转换为“int &”','  转换丢失限定符','非const引用绑定到const对象，如果允许的话，那么可以通过非const引用修改const原对象，\r\n这个出现一个矛盾，因此c++不允许执行此操作。解决方法就是使用非const引用绑定到同类型的非const对象，\r\n使用const引用绑定到不同但相关的类型的对象或者右值。','2016-11-15',1,2346,1234,66),(5,'TextQuery.cpp(63) : warning C4172: returning address of local variable or temporary','//返回单词出现的行号set  ','//返回单词出现的行号set  ','愿意是返回set对象的const引用以减轻复制set对象的负担，但是这里返回空的set对象的局部引用是错误的，\r\nc++ primer 原文采用的方法是返回set对象，\r\n不使用引用，这也是一种解决方法。另外使用std::vector<std::string>::size_type  比int型的set好。','2016-11-15',1,12,1212,12),(6,'“std::less<_Ty>::less”: 没有重载函数接受 2 个参数','“std::less<_Ty>::less”: 没有重载函数接受 2 个参数','“std::less<_Ty>::less”: 没有重载函数接受 2 个参数','错误代码示例:\r\n[cpp] view plaincopy \r\ntemplate<typename T,typename Compare=std::less<T> >  \r\nclass PriorityQueue : public Queue<T>  \r\n{  \r\n   template<typename T,typename Compare>  \r\nT PriorityQueue<T,Compare>::dequeue()  \r\n{  \r\n    if(index == 0)  \r\n    {  \r\n       std::cerr<<\"logic error : dequeue at empty queue. \"<<std::endl;  \r\n       throw std::logic_error(\"dequeue at empty queue\");  \r\n    }  \r\n    //pick up one with highest priority   \r\n    int highIndex = 0;  \r\n    for(int i = 1;i < index ;i++)    // O(n)  \r\n    {  \r\n        if( Compare( queue[i],queue[highIndex] ) )  \r\n            highIndex = i;  \r\n    }  \r\n    T result = queue[highIndex];  \r\n    index--;  \r\n    queue[highIndex] = queue[index];    //put the last element to the removed position  \r\n      \r\n    return result;  \r\n}  \r\n}  \r\n\r\n错误的代码出现在: if ( Compare( queue[i],queue[highIndex] ) )。\r\nCompare是一个函数对象，函数对象使用方式两种:\r\n第一种,显式构造一个函数对象，然后使用它,例如：\r\n[cpp] view plaincopy \r\nstd::less<Key> a;  //定义一个函数对象  \r\n  \r\nif(a(x,y)) ...     \r\n\r\n第二种，在使用时构造，例如：\r\n[cpp] view plaincopy \r\nif(std::less<Key>()(x,y)) ...  \r\n\r\n对于上述代码中，解决办法：代码更正为：if( Compare()( queue[i],queue[highIndex] ) )。\r\n原文出处：http://blog.csdn.net/wangdingqiaoit/article/details/8630900','2016-11-15',1,123,22,22),(7,'使用标准库sort等方法却未定义关系操作符  algorithm(3618) : error C2784: “bool std::operator <(const std::basic_string<_Elem,_Traits,_Alloc> &,const _Elem *)”: 未能从“Person”为“const std::basic_string','使用标准库sort等方法却未定义关系操作符','使用标准库sort等方法却未定义关系操作符  algorithm(3618) : error C2784: “bool std::operator <(const std::basic_string<_Elem,_Traits,_Alloc> &t,const _Elem *)”: 未能从“Person”为“cons','错误代码示例：\r\n[cpp] view plaincopy \r\n#include <vector>  \r\n#include <algorithm>  \r\n#include <iostream>  \r\n#include <string>  \r\n  \r\nclass Person  \r\n{  \r\npublic:  \r\n    // default constructor  \r\n    Person() : age(0) {}  \r\n    Person(int age, std::string name) {  \r\n        this->age = age; this->name = name;  \r\n    }  \r\n    int age;  \r\n    std::string name;  \r\n};  \r\n  \r\nint main()  \r\n{  \r\n    std::vector<Person> vecPerson;  \r\n    vecPerson.push_back(Person(24,\"Calvin\"));  \r\n    vecPerson.push_back(Person(30,\"Benny\"));  \r\n    vecPerson.push_back(Person(28,\"Alison\"));  \r\n  \r\n    std::sort(vecPerson.begin(),vecPerson.end());  \r\n    //using c++11  \r\n    for(const Person& p : vecPerson)  \r\n        std::cout<<p.age<<\", \"<<p.name<<std::endl;  \r\n  \r\n    return 0;  \r\n}  \r\n解决办法: 定义STL算法需要的关系操作符。在上例中，sort算法默认使用std::less，而std::less使用类的<操作符，因此可以定义一个全局的重载<操作符的函数来满足sort算法需求，如下:\r\n[cpp] view plaincopy \r\ninline bool operator<(const Person& a, const Person& b)  \r\n{  \r\n    return a.age < b.age;  \r\n}  \r\n\r\n程序输出:\r\n24, Calvin\r\n28, Alison\r\n30, Benny','2016-11-15',1,22,22,12),(8,'Struts Problem Report',' Struts Problem Report','运行时遇到了不能识别的标识的错误，包括类名，路径名，方法名等','如下面遇到了该错误信息：\r\n首先显示\r\nStruts Problem Report\r\nStruts has detected an unhandled exception:\r\n可以发现是这类错误，然后下面继续有详细的错误提示：\r\nUnresolved compilation problems: Question cannot be resolved to a type questions cannot be resolved\r\n	Could not instantiate bean class [com.exam.student.actions.UploadAction]: \r\n              Constructor threw exception; nested exception is java.lang.Error: Unresolved compilation problems: Question cannot be resolved to a type questions cannot be resolved\r\n由此可以发现应该是Question相关的变量questions的类型没有定义好，于是找到上面提示的UploadAction查看，发现\r\nprivate List questions;\r\n这里忘记了些List的类型，应该改成这样：\r\nprivate List<Question> questions;\r\n为了确保正确修改，重新部署项目，正常访问。\r\n遇到这类问题只能根据详细的提示信息查找相关代码仔细的检查了，包括看看有没有拼写错误，路径有没有写错，方法名，或是重启服务器，重新部署。','2016-11-15',1,12,12,12),(9,'java.net.SocketException: Unrecognized Windows Sockets error: 0: JVM_Bind','java.net.SocketException: Unrecognized Windows Sockets error: 0: JVM_Bind','服务器端口被占用','一：更改服务器的端口号；\r\n二：关闭占用当前端口的进程\r\n下边介绍第二种方法\r\n1.首先进入命令行 查看端口是否被占用  \r\n使用命令： netstat -ano\r\n我的服务器的端口是443 \r\n此端口已被PID为3432的进程占用\r\n \r\n2.查看该PID为 3432 的进程\r\n使用命令：tasklist|findstr \"3432\"\r\n由于在解决问题时，我把PID为3432的进程已关掉，所以演示时，我用2784来做\r\n \r\n可以看出 PID为2784的进程是 ConnectifyService.exe\r\n第一种解决办法是：把ConnectifyService.exe进程关掉\r\n第二种解决办法是：直接通过任务管理器查看PID为3432的进程，然后关掉\r\nwindows任务管理器->查看->选择列  选中PID 然后查看任务管理器，关掉PID为3432的进程','2016-11-15',1,12,12,34),(10,'ClassNotFoundException','ClassNotFoundException','原因：\r\n1 所需要的支持类库放错了地方，并没有放在类路径(CLASSPATH环境变量)里面。\r\n2 使用了重复的类库，且版本不一致。导致低版本的被优先使用。\r\n3 类名错了，一般是使用Class.forName的时候，手工指定了类名的情况。\r\n4 没有导入纯JAVA驱动包。','1 确认你的类库在系统的CLASSPATH下面，如果是Tomcat，则可以放在tomcat/lib/目录下面。\r\n2 删除重复的类库，只保留最新的。\r\n3 尽可能不使用这种编译器无法为你检查的方式。','2016-11-15',2,12,5,6),(11,' java.lang.NoSuchMethodException','.NoSuchMethodException','原因：\r\n1.Action 类的方法被定义成 private 类型.\r\n2.Action 类继承了 ActionSupport 类,时 程序在访问其方法时,也会抛出上述异常.','Action 中的类一定要在定义成public 类型,且不能继承 ActionSupport 类.','2016-11-15',2,12,12,12),(12,'NullPointException','NullPointException','试图访问一个空对象时','通过断点确定哪个对象为空，再找到该对象没有实例化的原因','2016-11-15',2,12,4,4),(13,'java.lang.IllegalArgumentException：Document base D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core','java.lang.IllegalArgumentException：Document base D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core','java.lang.IllegalArgumentException：Document base D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core','找到了：Tomcat服务器下的conf\\Catalina\\localhost下有一个.xml的文件，就是报错的那个文件名。删除掉，重新启动。','2016-11-17',2,111,1,1),(14,'tomcat启动时报:IOException while loading persisted sessions: java.io.EOFException','tomcat启动时报:IOException while loading persisted sessions: java.io.EOFException','EOFException表示输入过程中意外地到达文件尾或流尾的信号,导致从session中获取数据失败。','找到tomcat的根目录。 这是我tomcat的根目录：E:\\tomcat6_38\\work\\Catalina\\localhost下找到你的项目点进去，然后你会看到一个sessions.ser文件,  把此文件删除即可tomcat即可正常的使用。','2016-11-15',2,1,234,234),(15,'ClassCastException','ClassCastException','在集合中存入某对象，但是取出时却强转成其他对象','1.通过加断点的方法，查看集合存入的是什么对象，再和自己要强转的对象类型比较便知。\r\n2.泛型，已经限制了存入类型，就不会出现转换异常了。','2016-11-15',2,1,1,1),(16,'ArrayIndexOutOfBoundsException','ArrayIndexOutOfBoundsException','访问超过数组或集合最大索引值的数据','通过断点等方法，找到是否是循环超出了范围等原因。','2016-11-15',2,1,1,1),(17,'NumberFormatException','NumberFormatException','程序员认为变量是数值“123”，但那实际内容可能是“abc”','根据控制台报的异常，找到出错的位置，看异常中数据是什么，确定是否有问题。','2016-11-15',2,22,11,11),(18,'fatal error C1083: Cannot open include file: \'R…….h\': No such file or directory ','fatal error C1083: Cannot open include file: \'R…….h\': No such file or directory ','fatal error C1083: Cannot open include file: \'R…….h\': No such file or directory ','不能打开包含文件“R…….h”：没有这样的文件或目录。\r\n','2016-11-15',2,1,1,1),(19,'warning C4553: \'= =\' : operator has no effect; did you intend \'=\'? ','warning C4553: \'= =\' : operator has no effect; did you intend \'=\'? ','warning C4553: \'= =\' : operator has no effect; did you intend \'=\'? ','没有效果的运算符“= =”；是否改为“=”？','2016-11-15',2,1,12,12),(20,'XXXX : member function not declared in \'CHelloView\' ','XXXX : member function not declared in \'CHelloView\' ','XXXX : member function not declared in \'CHelloView\' ','成员函数“XXXX”没有在“CHelloView”中声明。','2016-11-15',2,12,1,2),(24,'类成员变量和函数重名问题   error C2365: “ArrayStack<T>::top”: 重定义；以前的定义是“成员函数”','类成员变量和函数重名问题   error C2365: “ArrayStack<T>::top”: 重定义；以前的定义是“成员函数”','这个错误发生纯属意外，但是让人纠结了半天。在c++中类的成员变量和函数是不可以重名的，这与Java之中不同。例如使用数组实现一个栈时，ArrayStack类的top指针成员和基类的函数top重名，导致的错误，一开始让人莫名其妙。','错误代码:\r\nStack基类中声明了函数top:\r\n[cpp] view plaincopy \r\ntemplate<typename T>  \r\nclass Stack  \r\n{  \r\npublic:  \r\n      virtual ~Stack(){};  \r\n      virtual void push(T data)=0;  \r\n      virtual T pop()=0;  \r\n      virtual T top()=0;  \r\n      virtual bool isEmpty()=0;  \r\n      virtual void clear() = 0;  \r\n      virtual int getSize()=0;  \r\n};  \r\n\r\n利用数组实现时:\r\n[cpp] view plaincopy \r\ntemplate<typename T = int>  \r\n  \r\nclass ArrayStack : public Stack<T>  \r\n{  \r\npublic:  \r\n      T top()  \r\n      {  \r\n         if(top == base)  \r\n        {  \r\n           throw std::logic_error(\"top at empty stack\");  \r\n        }  \r\n        return *(top-1);  \r\n      }  \r\n      //other member function  \r\nprivate:  \r\n      T *base,*top;  \r\n      int capacity;  \r\n};  \r\n因为类使用了指针top,同时实现了函数top，重名错误，因此导致了编译器给出如下错误信息:\r\n[plain] view plaincopy \r\nd:\\ds\\stack\\ArrayStack.h(79) : error C2365: “ArrayStack<T>::top”: 重定义；以前的定义是“成员函数”  \r\n  \r\n        d:\\ds\\stack\\ArrayStack.h(58) : 参见“ArrayStack<T>::top”的声明  \r\n        d:\\ds\\stack\\ArrayStack.h(81): 参见对正在编译的类 模板 实例化“ArrayStack<T>”的引用  \r\nd:\\ds\\stack\\ArrayStack.h(79) : error C2365: “ArrayStack<T>::top”: 重定义；以前的定义是“成员函数”  \r\n  \r\n        with  \r\n        [  \r\n            T=int  \r\n        ]  \r\n        d:\\ds\\stack\\ArrayStack.h(58) : 参见“ArrayStack<T>::top”的声明  \r\n        with  \r\n        [  \r\n            T=int  \r\n        ]  \r\n        StackTest.cpp(6): 参见对正在编译的类 模板 实例化“ArrayStack<T>”的引用  \r\n        with  \r\n        [  \r\n            T=int  \r\n        ]  \r\n解决办法，遵照合理的命名规则。','2016-11-15',1,1,1,1),(29,'\'initializing\' : cannot convert from \'char *\' to \'const ','\'initializing\' : cannot convert from \'char *\' to \'const ','/输出一行中所有字符  ','void printchar(string &line)  \r\n{  \r\n    istringstream iss(line);  \r\n    string word;  \r\n    while(iss>>word)  \r\n        for(vector<string>::const_iterator itbegin=word.begin(),itend=word.end();itbegin != itend; ++itbegin)  \r\n            cout<<*itbegin<<endl;  \r\n}  \r\n\r\n解决方法：标准库string对象可以使用迭代器操作 ，但是其迭代器要正确使用，应该使用string::const_iterator 后者使用下标操作来获取string对象中的字符。\r\n','2016-11-15',1,6,6,6),(30,' TextQuery.cpp(63) : warning C4172: returning address of local variable or temporary','//返回单词出现的行号set  ','//返回单词出现的行号set  const set<int> & TextQuery::RunQuery(string word) const  ','愿意是返回set对象的const引用以减轻复制set对象的负担，但是这里返回空的set对象的局部引用是错误的，\r\nc++ primer 原文采用的方法是返回set对象，\r\n不使用引用，这也是一种解决方法。另外使用std::vector<std::string>::size_type  比int型的set好。','2016-11-15',1,5,5,5),(31,'fatal error  \"vector iterator + offset out of range\" \"standard C++ libraries out of range \"','fatal error  \"vector iterator + offset out of range\" \"standard C++ libraries out of range \"','fatal error  \"vector iterator + offset out of range\" \"standard C++ libraries out of range \"','#include <iostream>  \r\n#include <iterator>//使用back_inserter   \r\n#include <algorithm>  \r\n#include <vector>  \r\nusing namespace std;  \r\nvoid main()  \r\n{       \r\n     vector<int> ivec;  \r\n     try  \r\n     {     \r\n        fill_n(ivec.begin(),10,1);//error  should use fill_n (back_inserter(ivec), 10, 1);   \r\n        for(vector<int>::iterator itbegin=ivec.begin(),itend=ivec.end();itbegin!=itend;++itbegin)  \r\n            cout<<*itbegin<<endl;  \r\n     }  \r\n     catch (runtime_error err)  \r\n     {    \r\n        cerr << \"Error: \"<<err.what()<<endl;  \r\n     }  \r\n     catch(out_of_range or)  \r\n     {  \r\n         cerr << \"Error: \"<<or.what()<<endl;  \r\n     }  \r\n     catch(exception ex)  \r\n     {  \r\n        cerr << \"Error: \"<<ex.what()<<endl;  \r\n     }  \r\n        \r\n解决方法：fill_n()函数将在vector中从头开始，将指定个数的元素设置为给定的值。fill_n函数假定对指定数量的元素做写操作是安全的。初学者常犯的错误的是：在没有元素的空容器上调用 fill_n 函数，因此需要使用back_inserter ，这种插入迭代器。当使用插入迭代器赋值时，则会在容器中添加一个新元素，其值等于赋值运算的右操作数的值。因此需将代码改为：\r\nfill_n (back_inserter(ivec), 10, 1);','2016-11-15',1,234,234,234);
/*!40000 ALTER TABLE `bug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bug_like`
--

DROP TABLE IF EXISTS `bug_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bug_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bug_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bug_like`
--

LOCK TABLES `bug_like` WRITE;
/*!40000 ALTER TABLE `bug_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `bug_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `commentContent` longtext,
  `commentPublishTime` datetime DEFAULT NULL,
  `commentAuthorId` int(11) DEFAULT NULL,
  `commentLikeNum` int(11) DEFAULT '0',
  `commentHateNum` int(11) DEFAULT '0',
  `bugId` int(11) DEFAULT NULL,
  `commentParentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'谢谢楼主，完美的解决了我的问题！','2016-11-22 00:00:00',1,11,0,30,NULL),(2,'哈哈，我的问题也解决了！','2016-11-22 00:00:00',2,5,0,30,1),(3,'心好累，解决不了评论的问题。','2016-11-22 00:00:00',1,2,0,30,NULL),(4,'不知道今天能不能写完评论了啊！','2016-11-22 00:00:00',2,22,0,30,NULL),(6,'<p>\r\n	testing what is this ?&nbsp;\r\n</p>\r\n<p>\r\n	ä¿ºä¸æåååï¼è½ä¸è½ç»ä¿ºè®²è®²\r\n</p>','2016-11-27 00:00:00',1,NULL,NULL,13,NULL),(8,'嘿嘿中文test','2016-11-27 00:00:00',1,NULL,NULL,13,NULL),(9,'time test1','2016-11-27 18:54:43',1,NULL,NULL,13,NULL),(11,'<p>\r\n	<span style=\"background-color:#E56600;\"><img src=\"/nullpointer/js/kindeditor/plugins/emoticons/images/1.gif\" border=\"0\" alt=\"\" /></span>\r\n</p>\r\n<p>\r\n	<span style=\"background-color:#E53333;\"></span>\r\n</p>','2016-11-27 18:58:41',1,NULL,NULL,13,NULL),(13,'<strong><em><u>包包，加粗0.0倾斜，下划线</u></em></strong>','2016-11-27 19:06:03',1,NULL,NULL,13,NULL),(14,'<span style=\"font-size:24px;\">123123</span><span style=\"font-size:24px;\"></span>','2016-11-27 19:12:36',1,NULL,NULL,13,NULL),(15,'456456456','2016-11-27 19:19:46',1,NULL,NULL,13,NULL),(16,'<span style=\"color:#E53333;\">1234567890-=234567890-=1234567890-</span>','2016-11-27 19:23:08',1,NULL,NULL,13,NULL),(17,'123123123123我是小个的','2016-11-27 19:25:02',1,NULL,NULL,13,NULL),(18,'<p>\r\n	任性的你比那号层了\r\n</p>\r\n<p>\r\n<pre class=\"prettyprint lang-java\">/**\r\n我是一只 消消腩\r\n*/</pre>\r\n</p>','2016-11-27 19:28:20',1,NULL,NULL,13,NULL),(19,'<ol>\r\n	<li>\r\n		<span style=\"line-height:1.5;\">0.0</span>\r\n	</li>\r\n	<li>\r\n		<span style=\"line-height:1.5;\">3.0</span>\r\n	</li>\r\n	<li>\r\n		<span style=\"line-height:1.5;\">4.0</span>\r\n	</li>\r\n</ol>','2016-11-27 21:07:20',1,NULL,NULL,13,18),(20,'<img src=\"/nullpointer/js/kindeditor/plugins/emoticons/images/12.gif\" border=\"0\" alt=\"\" />干啥','2016-11-27 21:38:16',1,NULL,NULL,13,11),(21,'<p>\r\n	原来如此？\r\n</p>\r\n<p>\r\n<pre class=\"prettyprint lang-js\">var char;</pre>\r\n</p>','2016-11-29 08:52:12',2,NULL,NULL,10,NULL),(22,'hhhhhhh','2016-11-29 08:53:09',2,NULL,NULL,10,21),(23,'asfdfsda','2016-11-29 08:53:17',2,NULL,NULL,10,NULL),(24,'afsdsfdadfsdf','2016-11-29 08:53:43',2,NULL,NULL,10,21),(25,'<pre class=\"prettyprint lang-java\">String arg = \"arg\";</pre>','2016-11-29 08:55:21',2,NULL,NULL,10,NULL),(26,'<pre class=\"prettyprint lang-java\">String arg= \"\";</pre>','2016-11-29 08:55:44',2,NULL,NULL,10,NULL),(27,'这楼是正解','2016-11-29 08:59:11',2,NULL,NULL,13,13);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loginuser`
--

DROP TABLE IF EXISTS `loginuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loginuser` (
  `loginName` varchar(30) DEFAULT NULL,
  `loginPassword` varchar(40) DEFAULT NULL,
  `loginEmail` varchar(30) DEFAULT NULL,
  `loginUserId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `loginActive` int(11) DEFAULT NULL,
  PRIMARY KEY (`loginUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginuser`
--

LOCK TABLES `loginuser` WRITE;
/*!40000 ALTER TABLE `loginuser` DISABLE KEYS */;
INSERT INTO `loginuser` VALUES ('烧鸡','4297f44b13955235245b2497399d7a93','1552068029@qq.com',1,2,1),('admin','4297f44b13955235245b2497399d7a93','626344962@qq.com',2,1,1);
/*!40000 ALTER TABLE `loginuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(40) DEFAULT NULL,
  `menuUrl` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `power`
--

DROP TABLE IF EXISTS `power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `power` (
  `powerId` int(11) NOT NULL AUTO_INCREMENT,
  `powerName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`powerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power`
--

LOCK TABLES `power` WRITE;
/*!40000 ALTER TABLE `power` DISABLE KEYS */;
/*!40000 ALTER TABLE `power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionId` int(11) NOT NULL AUTO_INCREMENT,
  `questionTitle` varchar(200) DEFAULT NULL,
  `questionDescribe` longtext,
  `questionPublishTime` datetime DEFAULT NULL,
  `questionAuthorId` int(11) DEFAULT NULL,
  `questionLikeNum` int(11) DEFAULT '0',
  `questionHateNum` int(11) DEFAULT '0',
  `questionAnswerCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'java中 << 什么意思？比如1<<30,谢谢哦~~','java中 << 什么意思？比如1<<30,谢谢哦~~','2011-12-14 00:00:00',1,25,4,35),(2,'java软件工程师应该具备哪些基本素质？','java软件工程师应该具备哪些基本素质？','2014-10-15 00:00:00',1,42,5,21),(3,'java编写程序获取指定文件的最后修改时间','java编写程序获取指定文件的最后修改时间','2016-10-05 08:15:00',1,31,4,25),(4,'C\\C++为什么不能返回函数内部new分配的内存的引用？','C\\C++为什么不能返回函数内部new分配的内存的引用？','2015-08-05 00:00:00',1,55,2,21),(5,'在C++中if(str.find(\"哦\")!=string::npos)是什么意...','在C++中if(str.find(\"哦\")!=string::npos)是什么意...','2016-10-05 00:00:00',1,22,0,21),(6,'c++如何将数组压入队列中','c++如何将数组压入队列中','2016-10-05 00:00:00',1,32,3,21);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_role_power`
--

DROP TABLE IF EXISTS `r_role_power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_role_power` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `powerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_role_power`
--

LOCK TABLES `r_role_power` WRITE;
/*!40000 ALTER TABLE `r_role_power` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_role_power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_tag_bug`
--

DROP TABLE IF EXISTS `r_tag_bug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_tag_bug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bugId` int(11) DEFAULT NULL,
  `tagId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_tag_bug`
--

LOCK TABLES `r_tag_bug` WRITE;
/*!40000 ALTER TABLE `r_tag_bug` DISABLE KEYS */;
INSERT INTO `r_tag_bug` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,1),(8,8,1),(9,9,3),(10,10,1),(11,11,3),(12,12,1),(13,13,1),(14,14,3),(15,15,1),(16,16,3),(17,17,1),(18,18,1),(19,19,1),(20,20,1),(21,21,1),(22,22,1),(23,23,1),(24,24,1),(25,25,3),(26,26,1),(27,27,1),(28,28,3),(29,29,3),(30,30,3),(31,1,2),(32,13,4),(33,31,2),(34,31,4);
/*!40000 ALTER TABLE `r_tag_bug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_tag_question`
--

DROP TABLE IF EXISTS `r_tag_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_tag_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questionId` int(11) DEFAULT NULL,
  `tagId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_tag_question`
--

LOCK TABLES `r_tag_question` WRITE;
/*!40000 ALTER TABLE `r_tag_question` DISABLE KEYS */;
INSERT INTO `r_tag_question` VALUES (1,1,1),(2,2,2),(3,3,1),(4,4,2),(5,5,1),(6,6,6),(7,1,3),(8,6,4);
/*!40000 ALTER TABLE `r_tag_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r_tag_userinfo`
--

DROP TABLE IF EXISTS `r_tag_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r_tag_userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userInfoId` int(11) DEFAULT NULL,
  `tagId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r_tag_userinfo`
--

LOCK TABLES `r_tag_userinfo` WRITE;
/*!40000 ALTER TABLE `r_tag_userinfo` DISABLE KEYS */;
INSERT INTO `r_tag_userinfo` VALUES (1,6,1),(2,6,2),(3,6,3);
/*!40000 ALTER TABLE `r_tag_userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'管理员'),(2,'用户');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tagId` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'C++'),(2,'JAVA'),(3,'Hibernate'),(4,'html'),(5,'C'),(6,'php'),(7,'servlet'),(8,'R语言'),(9,'大数据'),(10,'ios'),(11,'安卓'),(12,'javascript');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `userInfoId` int(11) NOT NULL AUTO_INCREMENT,
  `userInfoBirthday` date DEFAULT NULL,
  `userInfoHeadPortrait` text,
  `userInfoSex` varchar(2) DEFAULT NULL,
  `userInfoRegistTime` date DEFAULT NULL,
  `userInfoDescribe` varchar(200) DEFAULT NULL,
  `userInfoHonorCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`userInfoId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'1996-11-01','default.jpg','','2016-11-24','',10),(2,'1996-11-01','default.jpg','男','2016-11-24','大神！',10),(6,NULL,'default.jpg','男','2016-11-24','我一点也不懒',10);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'nullpointer'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-29 14:53:46
