package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class PirmasKomponentas implements Serializable {

    private static final long serialVersionUID = 4201431337752976155L;

    @Inject
    private Antras antras;

    public String sakykLabas() {
        return "Labas " + new Date() + " " + toString();
    }

    @PostConstruct
    public void init() {
        System.out.println("\n\n\n");
        System.out.println(antras.getClass().getName());
        System.out.println("\n\n\n");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die!");
    }
}
