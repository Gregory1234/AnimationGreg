package greg.gfx.swing

import greg.RunningObject
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JComponent
import javax.swing.JFrame

class JFrameWindowManager(t : String,s : Dimension) : RunningObject{
    @Volatile override var running = false
    private val f = JFrame()
    val component = object : JComponent(){}
    init{
        f.title = t
        component.preferredSize = s
        f.add(component)
        f.pack()
        f.setLocationRelativeTo(null)
        f.defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
        f.addWindowListener(object : WindowAdapter(){
            override fun windowClosing(e: WindowEvent?) {
                running = false
            }
        })
    }

    override fun setup() {
        running = true
        f.isVisible = true
    }

    override fun update() {

    }

    override fun cleanup() {
        f.isVisible = false
        f.dispose()
    }


}