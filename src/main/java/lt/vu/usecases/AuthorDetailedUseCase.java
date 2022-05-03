package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Author;
import lt.vu.persistence.AuthorDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class AuthorDetailedUseCase {

    @Inject
    AuthorDAO authorDAO;

    @Getter @Setter
    private Author author;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long authorId = Long.parseLong(requestParameters.get("authorId"));
        this.author = authorDAO.findOne(authorId);
    }

}
