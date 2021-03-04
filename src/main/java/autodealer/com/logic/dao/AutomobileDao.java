package autodealer.com.logic.dao;

import autodealer.com.logic.entity.*;

import java.util.ArrayList;

/**@author Haitanov Nikita*/

public interface AutomobileDao {

	 ArrayList<Automobile> readAllAutomobiles();
	 ArrayList<Automobile> queryAboutAuto(String querySqlString);
	 void createAutomobile(Automobile automobile);
	 void updateAutomobile(Automobile automobile);
	 void deleteAutomobile(Automobile automobile);
}
