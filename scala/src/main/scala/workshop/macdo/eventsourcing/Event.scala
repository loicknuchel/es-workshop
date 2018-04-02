package workshop.macdo.eventsourcing

trait Event {
  def meta: Meta
}

case class Meta(id: Long)
