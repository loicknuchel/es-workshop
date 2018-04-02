package workshop.macdo.storage

import workshop.macdo.eventsourcing.{Event, EventStore}

import scala.concurrent.Future

class InMemoryEventStore(initEvents: Seq[Event]) extends EventStore[Event] {
  private var nextId: Long = initEvents.lastOption.map(_.meta.id + 1).getOrElse(0l)
  private var events: Seq[Event] = initEvents

  override def getEvents(): Future[Seq[Event]] =
    Future.successful(events)

  override def saveEvents(events: Seq[Event]): Future[Unit] = {
    val ids: Seq[Long] = events.map(_.meta.id)
    val expectedIds: Seq[Long] = this.nextId until this.nextId + events.length
    if (ids == expectedIds) {
      this.nextId = this.nextId + events.length
      this.events = this.events ++ events
      Future.successful(())
    } else {
      Future.failed(new Exception(s"Expect ids ${expectedIds.mkString(", ")} but get ${ids.mkString(", ")}"))
    }
  }
}
