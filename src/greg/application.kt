package greg

class ApplicationManager(val app : Application) : RunningObject{
    override var running
        get() = app.engine.running
        set(x){
            app.engine.running=x
        }
    override fun setup(){
        app.setup()
        app.engine.setup()
        app.objects.forEach { it.setup() }
    }
    override fun update(){
        app.update()
        app.engine.update()
        app.objects.forEach { it.update() }
        app.postUpdate()
    }
    override fun cleanup(){
        app.objects.reversed().forEach { it.cleanup() }
        app.engine.cleanup()
        app.cleanup()
    }
}

abstract class Application : LiveObject{
    lateinit var engine : RunningObject
    val objects = mutableListOf<LiveObject>()
    abstract fun postUpdate()
}