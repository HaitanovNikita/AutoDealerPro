package autodealer.com.logic.service;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AutomobileService<T> {

    List<T> queryAboutAuto(@NotNull String querySqlString);

    List<T> getMostPopularAuto();
}
