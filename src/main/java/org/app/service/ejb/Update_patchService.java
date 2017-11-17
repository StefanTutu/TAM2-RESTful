package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Update_patch;

@Remote
public interface Update_patchService {
	
	Update_patch addUpdate_patch(Update_patch update_patchToAdd);
	
	String removeUpdate_patch(Update_patch update_patchToDelete);
	
	Update_patch getUpdate_patchByUpdate_patchID(Integer patchId);
	
	Collection<Update_patch> getUpdate_patch();
	
	String sayRest();

	Update_patch addUpdate_patch1(Update_patch update_patchToAdd);

	Update_patch addUpdate_patch11(Update_patch update_patchToAdd);

	Update_patch addUpdate_patch111(Update_patch update_patchToAdd);
}
