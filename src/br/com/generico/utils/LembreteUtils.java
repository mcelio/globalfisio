/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generico.utils;

import br.com.generico.dao.LembreteDao;
import br.com.generico.to.Lembrete;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcos
 */
public class LembreteUtils extends TimerTask {

    private static int status = 0;
    private static Logger logger = Logger.getLogger(LembreteUtils.class.getName());
    private SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    private static Long UMA_HORA = new Long(1000 * 60 * 60);
    private static Long MEIA_HORA = new Long(1000 * 60 * 30);
    private static Long DUAS_HORAS = new Long(1000 * 60 * 60 * 2);
    private static Long CINCO_SEGUNDOS = new Long(1000 * 5);
    private static LembreteUtils instancia;

    public static LembreteUtils getInstance() {
        if (instancia == null) {
            instancia = new LembreteUtils();
        }
        return instancia;
    }

    private LembreteUtils() {
    }

    public void lembrar() {
        //perform the task once a day at 4 a.m., starting tomorrow morning
        //(other styles are possible as well)
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, DUAS_HORAS, DUAS_HORAS);
    }

    @Override
    public void run() {
        logger.debug("####################################################");
        logger.debug("Executando funcionalidade lembrete!");
        logger.debug("####################################################");
        try {
            List<Lembrete> lista = new LembreteDao().listarAvisos();
            if (lista != null && !lista.isEmpty()) {
                StringBuffer buffer = new StringBuffer();
                for (Lembrete lembrete : lista) {
                    buffer.append(formataData.format(lembrete.getData()) + " - " + lembrete.getDescricao() + "\n\n");
                }
                if (status == 0) {
                    status = -1;
                    Object[] options = {"OK"};
                    status = JOptionPane.showOptionDialog(null, buffer.toString(), "Lembretes",
                            JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null,
                            options, options[0]);                    
                    //  status = JOptionPane.showConfirmDialog(null, buffer.toString());
                }
            }
        } catch (Exception e) {
            logger.error("Erro ao executar lembrete");
            return;
        }
    }
}
