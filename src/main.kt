import greg.*
import greg.gfx.*
import greg.gfx.swing.*
import java.awt.*


fun main(args : Array<String>){
    ApplicationManager(object : Application(){
        lateinit var renderer : RenderManager<Renderer2D>
        override fun setup(){
            val win = JFrameWindowManager("Test", Dimension(800,600))
            engine = win
            renderer = GraphicsRenderManager(win.component)
            objects.add(renderer)
        }
        override fun update(){
            renderer.add(BackgroundRenderable(Color.CYAN))
            renderer.add(object : Renderable<Renderer2D>{
                override fun render(renderer: Renderer2D) {
                    renderer.fillEllipse(Point(100,100),Dimension(200,100))
                }
            })
        }
        override fun postUpdate(){

        }
        override fun cleanup(){

        }
    }).run()
}



