package persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.Group;

public class GroupPersistenceService extends PersistenceService<Group> {

	private static GroupPersistenceService instance;

	public static GroupPersistenceService getInstance()
	{
		return instance = instance != null ? instance : new GroupPersistenceService();
	}
	private GroupPersistenceService() {};


	/**
	 *
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Group> getSearch(String search) {
		return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
			Query query = entityManager.createQuery("SELECT g FROM Group g WHERE groupName LIKE '%" + search + "%'");
			List<Group> groups = query.getResultList();
			if (groups.isEmpty()) {
				throw new NoResultException();
			} else {
				return groups;
			}
		});
	}

}
