package workshop.macdo.eventsourcing

import scala.concurrent.Future

trait CommandHandler[C, E] {
  def handle(command: C): Future[Either[Seq[CommandError], Seq[E]]]
}
