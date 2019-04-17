package greg.gfx

import greg.LiveObject
import java.awt.Color
import java.awt.Dimension
import java.awt.Image
import java.awt.Point

interface Renderable<in R>{
    fun render(renderer : R)
}

abstract class RenderManager<out R> : LiveObject{
    private val queue = mutableListOf<Renderable<R>>()
    fun add(r : Renderable<R>){
        queue.add(r)
    }
    abstract fun preRender()
    abstract fun postRender()
    abstract fun render(r : Renderable<R>)
    override fun update(){
        preRender()
        queue.forEach { render(it) }
        postRender()
        queue.clear()
    }
}

interface Renderer2D{
    var color : Color
    val size : Dimension
    fun drawRect(pos : Point,s : Dimension)
    fun drawEllipse(pos : Point,s : Dimension)
    fun drawLine(a : Point,b : Point)
    fun fillRect(pos : Point,s : Dimension)
    fun fillEllipse(pos : Point,s : Dimension)
    fun fillBackground()
    fun drawImage(i : Image, pos : Point)
    fun drawImage(i : Image, pos : Point,s : Dimension)
}

class BackgroundRenderable(val c : Color) : Renderable<Renderer2D>{
    override fun render(renderer: Renderer2D) {
        renderer.color = c
        renderer.fillBackground()
    }
}
class ImageRenderable(val i : Image,val p:Point,val s : Dimension) : Renderable<Renderer2D>{
    constructor(i : Image,p : Point) : this(i,p, Dimension(i.getWidth(null),i.getHeight(null)))
    override fun render(renderer: Renderer2D) {
        renderer.drawImage(i,p,s)
    }
}
