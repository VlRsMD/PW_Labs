import akka.actor.{Actor, ActorSystem, Props}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import scala.collection.mutable.ListBuffer

object search_engine {
  def history_search = {
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
    for (i: Int <-0 to urlList.length-1) {
      println(urlList(i))
    }
  }
  def science_search = {
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
    for (i: Int <-0 to urlList.length-1) {
      println(urlList(i))
    }
  }
  def technology_search = {
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
    for (i: Int <-0 to urlList.length-1) {
      println(urlList(i))
    }
  }
}

class CLI extends Actor {
  def receive = {
    case List("go2web", "-u", url: String) => {
      var doc: Document = Jsoup.connect(url).get();
      val data :Elements = doc.select("div")
      val dataListB: ListBuffer[String] = new ListBuffer[String]
      val data_arr = data.toArray()
      for (i<-0 to data_arr.length-1) {
        var data_s: Document = Jsoup.parse(data_arr(i).toString)
        var d: String = data_s.text()
        dataListB += d
      }
      val dataList = dataListB.toList
      for (i<-0 to dataList.length-1) {
        println(dataList(i))
      }
      val cli_actor = ActorSystem().actorOf(Props(new CLI))
      var c: String = scala.io.StdIn.readLine()
      var c_s = c.split(" ");
      val c_s_LB: ListBuffer[String] = new ListBuffer[String]
      for (i: Int <-0 to c_s.length-1) {
        c_s_LB += c_s(i)
      }
      val c_s_l = c_s_LB.toList
      cli_actor ! c_s_l
    }

    case List("go2web", "-s", search_term: String) => {
      if (search_term.contains("history")) {
        search_engine.history_search
        val cli_actor = ActorSystem().actorOf(Props(new CLI))
        var c: String = scala.io.StdIn.readLine()
        var c_s = c.split(" ");
        val c_s_LB: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to c_s.length-1) {
          c_s_LB += c_s(i)
        }
        val c_s_l = c_s_LB.toList
        cli_actor ! c_s_l
      } else if (search_term.contains("science")) {
        search_engine.science_search
        val cli_actor = ActorSystem().actorOf(Props(new CLI))
        var c: String = scala.io.StdIn.readLine()
        var c_s = c.split(" ");
        val c_s_LB: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to c_s.length-1) {
          c_s_LB += c_s(i)
        }
        val c_s_l = c_s_LB.toList
        cli_actor ! c_s_l
      } else if (search_term.contains("technology")) {
        search_engine.technology_search
        val cli_actor = ActorSystem().actorOf(Props(new CLI))
        var c: String = scala.io.StdIn.readLine()
        var c_s = c.split(" ");
        val c_s_LB: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to c_s.length-1) {
          c_s_LB += c_s(i)
        }
        val c_s_l = c_s_LB.toList
        cli_actor ! c_s_l
      } else {
        println("Unable to find relevant resources")
        val cli_actor = ActorSystem().actorOf(Props(new CLI))
        var c: String = scala.io.StdIn.readLine()
        var c_s = c.split(" ");
        val c_s_LB: ListBuffer[String] = new ListBuffer[String]
        for (i: Int <-0 to c_s.length-1) {
          c_s_LB += c_s(i)
        }
        val c_s_l = c_s_LB.toList
        cli_actor ! c_s_l
      }
    }

    case List("go2web", "-h") => {
      println("go2web -u <URL>                  print the response from the specified URL")
      println("go2web -s <search-term>          search the specified term and print top 10 results")
      println("go2web -h                         show  this help")
      val cli_actor = ActorSystem().actorOf(Props(new CLI))
      var c: String = scala.io.StdIn.readLine()
      var c_s = c.split(" ");
      val c_s_LB: ListBuffer[String] = new ListBuffer[String]
      for (i: Int <-0 to c_s.length-1) {
        c_s_LB += c_s(i)
      }
      val c_s_l = c_s_LB.toList
      cli_actor ! c_s_l
    }
  }
}

object cli_app extends App {
  val system = ActorSystem()
  val cli_actor = system.actorOf(Props(new CLI))
  var c: String = scala.io.StdIn.readLine()
  var c_s = c.split(" ");
  val c_s_LB: ListBuffer[String] = new ListBuffer[String]
  for (i: Int <-0 to c_s.length-1) {
    c_s_LB += c_s(i)
  }
  val c_s_l = c_s_LB.toList
  cli_actor ! c_s_l
}
