package com.pluralsight.dao;

import com.pluralsight.model.vehicle.*;
import java.util.List;

public interface DealershipDAO {
    Dealership findDealershipById(int id);
    List<Dealership> findAllDealerships();


}
