import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.ServerSocket
import java.net.Socket
import java.net.UnknownHostException
import akka.actor.{Actor, ActorSystem, Props}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.{BufferedInputStream, BufferedOutputStream, InputStream, PrintStream}
import scala.collection.mutable.ListBuffer
import java.net.{URL, URLConnection}
import java.nio.charset.StandardCharsets
import java.util.Scanner

object search_engine {
  def history_search: List[String] = {
    val urlListB: ListBuffer[String] = new ListBuffer[String];
    urlListB += "https://www.history.com/"
    urlListB += "https://www.historytoday.com/archive/head-head/what-history"
    urlListB += "http://www.rbs0.com/wh.pdf"
    urlListB += "https://owlcation.com/humanities/What-is-History-Introducing-History-For-Kids"
    urlListB += "https://www.thoughtco.com/what-is-history-collection-of-definitions-171282"
    urlListB += "https://www.britannica.com/topic/history"
    urlListB += "https://alphahistory.com/what-is-history/"
    urlListB += "https://www.bbc.co.uk/history/british/"
    urlListB += "https://www.goodreads.com/list/show/1362.Best_History_Books_"
    urlListB += "https://www.worldhistory.org/mapselect/"
    val urlList = urlListB.toList;
    return urlList
  }
  def science_search: List[String] = {
    val urlListB: ListBuffer[String] = new ListBuffer[String];
    urlListB += "https://www.sciencenews.org/"
    urlListB += "https://www.nationalgeographic.com/science/"
    urlListB += "https://spaceplace.nasa.gov/science/en/"
    urlListB += "https://undsci.berkeley.edu/understanding-science-101/what-is-science/"
    urlListB += "https://www.sciencedirect.com/"
    urlListB += "https://simplicable.com/science/science"
    urlListB += "https://www.edx.org/learn/science"
    urlListB += "https://www.researchgate.net/publication/329321111_Introduction_to_Science"
    urlListB += "https://www.sciencefocus.com/books/science-books-2/"
    urlListB += "https://www.sciencemadesimple.com/science-definition.html"
    val urlList = urlListB.toList;
    return urlList
  }
  def technology_search: List[String] = {
    val urlListB: ListBuffer[String] = new ListBuffer[String];
    urlListB += "https://www.britannica.com/technology/technology"
    urlListB += "https://www.bbc.com/news/technology"
    urlListB += "https://www.simplilearn.com/top-technology-trends-and-jobs-article"
    urlListB += "https://www.newscientist.com/subject/technology/"
    urlListB += "https://www.wired.co.uk/topic/technology"
    urlListB += "https://www.technologyreview.com/"
    urlListB += "https://www.nytimes.com/section/technology"
    urlListB += "https://www.livescience.com/technology"
    urlListB += "https://www.indeed.com/career-advice/finding-a-job/types-of-technology"
    urlListB += "https://www.weforum.org/agenda/2020/11/heres-how-technology-has-changed-and-changed-us-over-the-past-20-years/"
    val urlList = urlListB.toList;
    return urlList
  }
}

object request {
  def startSever(): Unit = {
    (new Thread() {
      @Override
      override def run() {
        var ss: ServerSocket = new ServerSocket()
        try {
          ss = new ServerSocket(7020)
          var s: Socket = ss.accept();
          var in: BufferedReader = new BufferedReader(
            new InputStreamReader(s.getInputStream()));
          var line: String = null;
          while ((line = in.readLine()) != null) {
            System.out.println(line);
          }
        } catch {
          case io: IOException => {
            io.printStackTrace()
          }
        }
      }
    }).start();
  }
  def startSender(): Unit = {
    (new Thread() {
      @Override
      override def run() {
        try {
          var s: Socket = new Socket("localhost", 7020);
          var out: BufferedWriter = new BufferedWriter(
            new OutputStreamWriter(s.getOutputStream()));
          out.write("This is PW lab nr. 2 project.");
          out.newLine();
          out.flush();
        } catch {
          case u: UnknownHostException => {
            u.printStackTrace()
          }
          case io: IOException => {
            io.printStackTrace()
          }
          case i: InterruptedException => {
            i.printStackTrace()
          }
        }
      }
    }).start();
  }
}

class CLI extends Actor {
  def receive = {
    case List("go2web", "-u", "http://localhost:7020/") => {
      request.startSever()
      request.startSender()
      val cliActor = ActorSystem().actorOf(Props(new CLI))
      var command: String = scala.io.StdIn.readLine()
      var commandSplit = command.split(" ");
      val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
      for (i: Int <-0 to commandSplit.length-1) {
        commandSplitListBuffer += commandSplit(i)
      }
      val commandSplitList = commandSplitListBuffer.toList
      cliActor ! commandSplitList
    }

    case List("go2web", "-s", search_term: String) => {
      if (search_term.contains("history")) {
        for(i<-search_engine.history_search.indices) {
          println(search_engine.history_search(i))
        }
        val cliActor = ActorSystem().actorOf(Props(new CLI))
        var command: String = scala.io.StdIn.readLine()
        var commandSplit = command.split(" ");
        val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to commandSplit.length-1) {
          commandSplitListBuffer += commandSplit(i)
        }
        val commandSplitList = commandSplitListBuffer.toList
        cliActor ! commandSplitList
      } else if (search_term.contains("science")) {
        for(i<-search_engine.history_search.indices) {
          println(search_engine.history_search(i))
        }
        val cliActor = ActorSystem().actorOf(Props(new CLI))
        var command: String = scala.io.StdIn.readLine()
        var commandSplit = command.split(" ");
        val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to commandSplit.length-1) {
          commandSplitListBuffer += commandSplit(i)
        }
        val commandSplitList = commandSplitListBuffer.toList
        cliActor ! commandSplitList
      } else if (search_term.contains("technology")) {
        for(i<-search_engine.history_search.indices) {
          println(search_engine.history_search(i))
        }
        val cliActor = ActorSystem().actorOf(Props(new CLI))
        var command: String = scala.io.StdIn.readLine()
        var commandSplit = command.split(" ");
        val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to commandSplit.length-1) {
          commandSplitListBuffer += commandSplit(i)
        }
        val commandSplitList = commandSplitListBuffer.toList
        cliActor ! commandSplitList
      } else {
        println("Unable to find relevant resources")
        val cliActor = ActorSystem().actorOf(Props(new CLI))
        var command: String = scala.io.StdIn.readLine()
        var commandSplit = command.split(" ");
        val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to commandSplit.length-1) {
          commandSplitListBuffer += commandSplit(i)
        }
        val commandSplitList = commandSplitListBuffer.toList
        cliActor ! commandSplitList
      }
    }

    case List("go2web", "-s", "-u", search_term: String) => {
      if (search_term.contains("history")) {
        for(i<-search_engine.history_search.indices) {
          println(search_engine.history_search(i))
        }
        request.startSever()
        request.startSender()
        val cliActor = ActorSystem().actorOf(Props(new CLI))
        var command: String = scala.io.StdIn.readLine()
        var commandSplit = command.split(" ");
        val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to commandSplit.length-1) {
          commandSplitListBuffer += commandSplit(i)
        }
        val commandSplitList = commandSplitListBuffer.toList
        cliActor ! commandSplitList
      } else if (search_term.contains("science")) {
        for(i<-search_engine.history_search.indices) {
          println(search_engine.history_search(i))
        }
        request.startSever()
        request.startSender()
        val cliActor = ActorSystem().actorOf(Props(new CLI))
        var command: String = scala.io.StdIn.readLine()
        var commandSplit = command.split(" ");
        val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to commandSplit.length-1) {
          commandSplitListBuffer += commandSplit(i)
        }
        val commandSplitList = commandSplitListBuffer.toList
        cliActor ! commandSplitList
      } else if (search_term.contains("technology")) {
        for(i<-search_engine.history_search.indices) {
          println(search_engine.history_search(i))
        }
        request.startSever()
        request.startSender()
        val cliActor = ActorSystem().actorOf(Props(new CLI))
        var command: String = scala.io.StdIn.readLine()
        var commandSplit = command.split(" ");
        val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to commandSplit.length-1) {
          commandSplitListBuffer += commandSplit(i)
        }
        val commandSplitList = commandSplitListBuffer.toList
        cliActor ! commandSplitList
      } else {
        println("Unable to find relevant resources")
        val cliActor = ActorSystem().actorOf(Props(new CLI))
        var command: String = scala.io.StdIn.readLine()
        var commandSplit = command.split(" ");
        val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to commandSplit.length-1) {
          commandSplitListBuffer += commandSplit(i)
        }
        val commandSplitList = commandSplitListBuffer.toList
        cliActor ! commandSplitList
      }
    }

    case List("go2web", "-h") => {
      println("go2web -u <URL>                      print the response from the specified URL")
      println("go2web -s <search-term>              search the specified term and print top 10 results")
      println("go2web -s -u <search-term>           search the specified term and print each of the top 10 results together with response from correspondent URL")
      println("go2web -h                            show  this help")
      val cliActor = ActorSystem().actorOf(Props(new CLI))
      var command: String = scala.io.StdIn.readLine()
      var commandSplit = command.split(" ");
      val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
      for (i: Int <-0 to commandSplit.length-1) {
        commandSplitListBuffer += commandSplit(i)
      }
      val commandSplitList = commandSplitListBuffer.toList
      cliActor ! commandSplitList
    }
  }
}

object cli_app extends App {
  val system = ActorSystem()
  val cliActor = ActorSystem().actorOf(Props(new CLI))
  var command: String = scala.io.StdIn.readLine()
  var commandSplit = command.split(" ");
  val commandSplitListBuffer: ListBuffer[String] = new ListBuffer[String]
  for (i: Int <-0 to commandSplit.length-1) {
    commandSplitListBuffer += commandSplit(i)
  }
  val commandSplitList = commandSplitListBuffer.toList
  cliActor ! commandSplitList
}
