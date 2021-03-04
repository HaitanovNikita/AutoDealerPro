package autodealer.com.logic.dao;

import autodealer.com.logic.entity.*;

import java.util.ArrayList;

public interface ClientDao {

	public void createClient(Client client);
	public void updateClient(Client client);
	public void deleteClient(Client client);
	public Client findClient(int idClient);
	public ArrayList<Client> readAllClients();
	
}
