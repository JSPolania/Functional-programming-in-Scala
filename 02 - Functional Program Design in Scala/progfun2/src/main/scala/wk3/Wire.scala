package wk3



class Wire {
  type Action = () => Unit
  private var signal = false
  private var actions: List[Action] = List()
}
