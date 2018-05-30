package persistence;

import entities.BundesligaTable;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 *
 */
public class BundesligaTablePersistenceService extends PersistenceService<BundesligaTable> {

    private static BundesligaTablePersistenceService instance;

    /**
     *
     * @return
     */
    public static BundesligaTablePersistenceService getInstance()
    {
        return instance = instance != null ? instance : new BundesligaTablePersistenceService();
    }

    /**
     *
     * @param leagueId
     * @return
     * @throws NoResultException
     */
    public List<BundesligaTable> getAllEntriesByLeagueId(final int leagueId) throws NoResultException{
        return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
            String qlString = "SELECT b FROM bundesligatable b WHERE leagueId = :leagueId";
            Query query = entityManager.createQuery(qlString);
            query.setParameter("leagueId", leagueId);
            List<BundesligaTable> bundesligaTableEntries = query.getResultList();
            if(bundesligaTableEntries.isEmpty())
                throw new NoResultException();
            else
                return bundesligaTableEntries;
        });
    }

    /**
     *
     * @param leagueId
     * @param pos
     * @return
     * @throws NoResultException
     */
    public BundesligaTable getEntryByPos(final int leagueId, final int pos) throws NoResultException{
        return JPAOperations.doInJPA(this::entityManagerFactory, entityManager -> {
            String qlString = "SELECT b FROM bundesligatable b " +
                              "WHERE leagueId = :leagueId AND leaguePosition = :leaguePos";
            Query query = entityManager.createQuery(qlString);
            query.setParameter("leagueId", leagueId);
            query.setParameter("leaguePos", pos);
            List<BundesligaTable> bundesligaTableEntries = query.getResultList();
            if(bundesligaTableEntries.isEmpty())
                throw new NoResultException();
            else
                return bundesligaTableEntries.get(0);
        });
    }
}