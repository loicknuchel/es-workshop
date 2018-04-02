package workshop.macdo.eventsourcing

trait EventHandler[E] {
  def handle(event: E): Unit
}
