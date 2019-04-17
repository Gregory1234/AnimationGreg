package greg

interface LiveObject{
    fun setup()
    fun update()
    fun cleanup()
}

interface RunningObject : LiveObject{
    var running : Boolean
    fun run(){
        setup()
        while(running)
            update()
        cleanup()
    }
}