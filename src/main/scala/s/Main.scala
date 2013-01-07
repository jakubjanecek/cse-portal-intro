package s

import annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {
    println("CASE CLASSES")
    val currentTime = System.currentTimeMillis
    val ev1 = Event(1, "event1", currentTime, Device1)
    val ev2 = Event(2, "event2", System.currentTimeMillis, Device2)
    val ev3 = Event(3, "event3", System.currentTimeMillis, Device1)

    println(ev1 == Event(1, "event1", currentTime, Device1))
    println(ev3)

    println()
    println("PATTERN MATCHING")
    val queue = List(ev1, ev2, ev3)

    @tailrec
    def handleEvents(queue: List[s.Event]): Unit = {

      def processEvent(ev: s.Event): Unit = {
        println("Processing event: " + ev)
        ev match {
          case Event(_, "event2", _, d) => println("'event2' from device " + d)
          case Event(_, _, when, _) if when > System.currentTimeMillis() - 10000 => println("fresh event")
          case _ => // ignore
        }
      }

      queue match {
        case Nil => println("All events handled.")
        case head :: tail => {
          processEvent(head)
          handleEvents(tail)
        }
      }
    }

    handleEvents(queue)

    println(genericSize("test"))
    println(genericSize(Array(1, 2, 3)))
    println(genericSize(List(1, 2, 3)))
    println(genericSize(new ObjectWithSize))
    println(genericSize(new AnyRef))

    println()
    println("COLLECTIONS")
    val collection = 1 to 100

    val sum = collection.fold(0)(_ + _)
    println("sum: " + sum)

    val numberOfOdds = collection.filter(_ % 2 == 1).size
    println("Number of odds: " + numberOfOdds)

    println()
    println("OWN CONTROL STRUCTURES")
    import Transaction._

    tx {
      println("I am in transaction, safe and sound!")
    }

    tx {
      println("I am in transaction that is going to fail!")
      throw new RuntimeException("fail")
    }

    println()
    println("OPTION TYPE")
    val opt = Option("optional value")
    opt match {
      case Some(v) => println("value: " + v)
      case None => println("none")
    }

    val result1 = for {
      name <- Some("George")
      surname <- Some("Smith")
      age <- Some(23)
      termsAgreed <- Some(true)
    } yield FormResult(name, surname, age, termsAgreed)
    println(result1)

    val result2 = for {
      name <- Some("George")
      surname <- Some("Smith")
      age <- Some(23)
      termsAgreed <- None
    } yield FormResult(name, surname, age, termsAgreed)
    println(result2)
  }

  def genericSize(obj: AnyRef): Int = obj match {
    case x: String => x.length
    case x: Array[_] => x.length
    case x: List[_] => x.size
    case x: ObjectWithSize => x.count
    case _ => -1
  }

}
