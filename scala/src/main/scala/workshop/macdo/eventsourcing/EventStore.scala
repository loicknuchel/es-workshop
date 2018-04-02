package workshop.macdo.eventsourcing

import scala.concurrent.Future

trait EventStore[E] {
  def getEvents(): Future[Seq[E]]
  def saveEvents(events: Seq[E]): Future[Unit]
}
