package workshop.macdo.storage

import org.scalatest.{FunSpec, Matchers}
import workshop.macdo.eventsourcing.{Event, Meta}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Try

class InMemoryEventStoreSpec extends FunSpec with Matchers {
  def wait[A](f: Future[A]): A =
    Await.result(f, Duration.Inf)

  case class MyEvent1(name: String, meta: Meta) extends Event
  case class MyEvent2(value: Int, meta: Meta) extends Event

  describe("InMemoryEventStore") {
    it("should save events and get them back") {
      val initEvents = Seq()
      val events = Seq(
        MyEvent1("aaa", Meta(0)),
        MyEvent2(123, Meta(1)),
        MyEvent1("aaa", Meta(2)))
      val es = new InMemoryEventStore(initEvents)
      wait(es.saveEvents(events))
      wait(es.getEvents()) shouldBe initEvents ++ events
    }
    it("should save events and get them back with initial events") {
      val initEvents = Seq(
        MyEvent1("aaa", Meta(0)),
        MyEvent2(123, Meta(1)),
        MyEvent1("aaa", Meta(2)))
      val events = Seq(
        MyEvent2(123, Meta(3)),
        MyEvent1("aaa", Meta(4)))
      val es = new InMemoryEventStore(initEvents)
      wait(es.saveEvents(events))
      wait(es.getEvents()) shouldBe initEvents ++ events
    }
    it("should not accept events with repeated id") {
      val initEvents = Seq()
      val events = Seq(
        MyEvent1("aaa", Meta(0)),
        MyEvent2(123, Meta(1)),
        MyEvent1("aaa", Meta(1)))
      val es = new InMemoryEventStore(initEvents)
      Try(wait(es.saveEvents(events))).isFailure shouldBe true
    }
    it("should not accept events with disontinuous id") {
      val initEvents = Seq()
      val events = Seq(
        MyEvent1("aaa", Meta(0)),
        MyEvent2(123, Meta(2)),
        MyEvent1("aaa", Meta(3)))
      val es = new InMemoryEventStore(initEvents)
      Try(wait(es.saveEvents(events))).isFailure shouldBe true
    }
    it("should not accept if new events does not have ids following existing ones") {
      val initEvents = Seq(
        MyEvent1("aaa", Meta(0)),
        MyEvent2(123, Meta(1)),
        MyEvent1("aaa", Meta(2)))
      val events = Seq(
        MyEvent2(123, Meta(2)),
        MyEvent1("aaa", Meta(3)))
      val es = new InMemoryEventStore(initEvents)
      Try(wait(es.saveEvents(events))).isFailure shouldBe true
    }
  }
}
