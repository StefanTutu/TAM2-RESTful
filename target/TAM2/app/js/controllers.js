// main controller
app.controller('mainController', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("mainController");
	
	$scope.softwaresSelected = [];
	$scope.releasesList = [];
	$scope.releaseSelected = [];
	$scope.futureReleasesSelected=[];
	$scope.updatesSelected=[];
	$scope.teamsSelected=[];
	
	$http.get(softwaresRestURL)
	.success(function(data){
		console.log("mainController:GET URL : " + softwaresRestURL);
		$scope.softwaresList = data;
	});
}]);

// view1 controller
app.controller('view1Controller', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("view1Controller");

	
	$scope.gridOptions = { 
		      data: 'softwaresList',
		      selectedItems: $scope.softwaresSelected,
		      enablePaging: true,
		      multiSelect: false,
		      resizable: true,
		      showFilter: true, showColumnMenu:true,
		      sortInfo: { fields: ['applicationId'], directions: ['asc'] },
		      filterOptions: $scope.filterOptions,
		      heigh: 100,
		      columnDefs: [{ field: "applicationId", width: 120, displayName: 'No.'},
		                   { field: "applicationName", width: 300 , displayName: 'Software name' },
		                   { field: "devStage", width: 170 , displayName: 'Development stage' },
		                   { field: "releaseDate", width: 200 , displayName: 'Release date' },
		                   { field: "domain", width: 200 , displayName: 'Domain' },
		                   { field: "type", width: 200 , displayName: 'Type' },
		                   { field: "requirements", width: 300 , displayName: 'Requirements' }]   
		    };		
	
	$http.get(softwaresRestURL)
	.success(function(data){
		console.log("view1Controller:GET URL : " + softwaresRestURL);
		$scope.softwaresList = data;
	})
	.error(function(data){
		console.log('ERROR');
		console.log(data);
	});		
	
	$scope.view1_name = "Softwares list";
}]);


//view2 controller
app.controller('view2Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view2Controller");
	
    $scope.gridOptions = { 
      data: 'softwaresList',
      selectedItems: $scope.softwaresSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      sortInfo: { fields: ['applicationId'], directions: ['asc'] },
      heigh: 100,
      columnDefs: [{ field: "applicationId", width: 120, displayName: 'No.'},
                   { field: "applicationName", width: 300 , displayName: 'Software name' },
                   { field: "devStage", width: 170 , displayName: 'Development stage' },
                   { field: "releaseDate", width: 200 , displayName: 'Release date' },
                   { field: "domain", width: 200 , displayName: 'Domain' },
                   { field: "type", width: 200 , displayName: 'Type' },
                   { field: "requirements", width: 300 , displayName: 'Requirements' }]  
    };

	restResource.get(softwaresRestURL).then(function (data) {
		console.log(data);
		$scope.softwaresList = data;
		$timeout(function() {
			idx = 0;
			console.log("software to select [back]: ");
			console.log($scope.softwaresSelected[0]);			
			if ($scope.softwaresSelected[0] != null){
				for(i in $scope.softwaresList){
					if ($scope.softwaresList[i].applicationId == $scope.softwaresSelected[0].applicationId)
						idx = i;
				}
			}
			try{ $scope.gridOptions.selectRow(idx, true); } catch(e){}
		});
	});	    
    
    $scope.add = function(){
    	console.log("view2Controller: add action");
    	software = $scope.softwaresSelected[0];
    	newSoftware = JSON.parse(JSON.stringify(software));
  
    	newSoftware.applicationId=software.applicationId;
    	newSoftware.applicationName=software.applicationName;
    	newSoftware.devStage=software.devStage;
    	newSoftware.releaseDate=software.releaseDate;
    	newSoftware.domain=software.domain;
    	newSoftware.type=software.type;
    	newSoftware.requirements=software.requirements;
    	newSoftware.feature=[];
    	newSoftware.release=[];
    	
    	newSoftware.link.href = newSoftware.link.href.replace(software.applicationId, newSoftware.applicationId);
    	
    	//
    	$scope.softwaresList.push(newSoftware);
    	idx = $scope.softwaresList.indexOf(newSoftware);
    	
    	$timeout(function() {
    		console.log(idx);
    		$timeout(function() { $scope.gridOptions.selectRow(idx, true); });
    	}); 
    	restResource.post(newSoftware);
    	
    };
    
    $scope.save = function(){
    	console.log("view2Controller: save action");
    	if($scope.softwaresSelected[0] == null)
    		return;
    	software = $scope.softwaresSelected[0];
    	restResource.put(software);	
    };
    
    $scope.cancel = function(){
    	console.log("view2Controller: cancel action");
    };    
    
    $scope.remove = function(){
    	console.log("view2Controller: remove action");
    	software = $scope.softwaresSelected[0];
    	link = software.link.href;
    	
    	// remove local model
    	var idx = $scope.softwaresList.indexOf(software);      	
    	
    	restResource.remove(software).then(function(data){
    		$scope.softwaresList.splice(idx, 1);
    		$timeout(function() { $scope.gridOptions.selectRow(0, true); });    		
    	});
    	
    };    
    
    $scope.go = function ( path ) {
    	  $location.path( path );
    };
}]);

//view3 controller
app.controller('view3Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view3Controller");
	
    $scope.detailGrid = { 
      data: 'featureList',
      selectedItems: $scope.featureSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      width: 600,
      columnDefs: [{ field: "featureId", width: 120, displayName: 'ID', enableCellEdit: true},
                   { field: "name", width: 180 , displayName: 'Name', enableCellEdit: true },
                   { field: "category", width: 300 , displayName: 'Category', enableCellEdit: true },
                   { field: "description", width: 300 , displayName: 'Description', enableCellEdit: true }
                   ]
    };		
	
	// projects data model
    softwareRestURL = $scope.softwaresSelected[0].link.href;
	console.log("softwaresRestURL:::: " + softwareRestURL);
	//
	restResource.get(softwareRestURL).then(function (data) {
		console.log(data);
		$scope.software = data;
		$scope.featureList = $scope.software.feature;
		console.log("check resource: ");
		console.log(restResource.entity);
	});
	//
	$scope.go = function ( path ) {
  	  $location.path( path );
	};
	//
	$scope.save = function(){
    	console.log("view3Controller: save action");
    	console.log($scope.software);
    	if($scope.software == null)
    		return;  	
    	restResource.put();
    };
	
}]);

//view4 controller
app.controller('view4Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view4Controller");
	
    $scope.detailGrid2 = { 
      data: 'releasesList',
      selectedItems: $scope.releaseSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      width: 600,
      columnDefs: [{ field: "productId", width: 120, displayName: 'ID', enableCellEdit: true},
                   { field: "version", width: 180 , displayName: 'Version', enableCellEdit: true },
                   { field: "productState", width: 300 , displayName: 'Dev. state', enableCellEdit: true },
                   { field: "releaseDate", width: 300 , displayName: 'Release date', enableCellEdit: true }
                   ]
    };		
	
	// projects data model
    softwareRestURL = $scope.softwaresSelected[0].link.href;
	console.log("softwaresRestURL:::: " + softwareRestURL);
	//
	restResource.get(softwareRestURL).then(function (data) {
		console.log(data);
		$scope.software = data;
		$scope.releasesList = $scope.software.release;
		console.log("check resource: ");
		console.log(restResource.entity);
	});
	//
	$scope.go = function ( path ) {
  	  $location.path( path );
	};
	//
	$scope.save = function(){
    	console.log("view4Controller: save action");
    	console.log($scope.software);
    	if($scope.software == null)
    		return;  	
    	restResource.put();
    };
}]);

//view5 controller
app.controller('view5Controller', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("view5Controller");

	
	$scope.gridFutureReleases = { 
		      data: 'futureReleasesList',
		      selectedItems: $scope.futureReleasesSelected,
		      enablePaging: true,
		      multiSelect: false,
		      resizable: true,
		      showFilter: true, showColumnMenu:true,
		      sortInfo: { fields: ['releaseId'], directions: ['asc'] },
		      filterOptions: $scope.filterOptions,
		      heigh: 100,
		      columnDefs: [{ field: "releaseId", width: 120, displayName: 'No.'},
		                   { field: "type", width: 200 , displayName: 'Release type' },
		                   { field: "estimatedDate", width: 170 , displayName: 'Estimated date' },
		                   { field: "software.applicationName", width: 300 , displayName: 'Release for' }]
		    };		
	
	$http.get(futureReleasesRestURL)
	.success(function(data){
		console.log("view5Controller:GET URL : " + futureReleasesRestURL);
		$scope.futureReleasesList = data;
	})
	.error(function(data){
		console.log('ERROR');
		console.log(data);
	});		
	
	$scope.view5_name = "Future releases";
}]);


//view6 controller
app.controller('view6Controller', 
['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view6Controller");
	
  $scope.gridFutureReleases = { 
    data: 'futureReleasesList',
    selectedItems: $scope.futureReleasesSelected,
    enablePaging: true,
    multiSelect: false,
    resizable: true,
    sortInfo: { fields: ['releaseId'], directions: ['asc'] },
    heigh: 100,
    columnDefs: [{ field: "releaseId", width: 120, displayName: 'No.'},
                 { field: "type", width: 200 , displayName: 'Release type' },
                 { field: "estimatedDate", width: 170 , displayName: 'Estimated date' },
                 { field: "software.applicationName", width: 300 , displayName: 'Release for' }]
  };

	restResource.get(futureReleasesRestURL).then(function (data) {
		console.log(data);
		$scope.futureReleasesList = data;
		$timeout(function() {
			idx = 0;
			console.log("future release to select [back]: ");
			console.log($scope.futureReleasesSelected[0]);			
			if ($scope.futureReleasesSelected[0] != null){
				for(i in $scope.futureReleasesList){
					if ($scope.futureReleasesList[i].releaseId == $scope.futureReleasesSelected[0].releaseId)
						idx = i;
				}
			}
			try{ $scope.gridFutureReleases.selectRow(idx, true); } catch(e){}
		});
	});	    
  
  $scope.add = function(){
  	console.log("view6Controller: add action");
  	fRelease = $scope.futureReleasesSelected[0];
  	newfRelease = JSON.parse(JSON.stringify(fRelease));

  	newfRelease.releaseId=fRelease.releaseId;
  	newfRelease.type=fRelease.type;
  	newfRelease.estimatedDate=fRelease.estimatedDate;
  	newfRelease.feature=[];
  	newfRelease.software=[]
	
  	newfRelease.link.href = newfRelease.link.href.replace(fRelease.releaseId, newfRelease.releaseId);
  	
  	//
  	$scope.futureReleasesList.push(newfRelease);
  	idx = $scope.futureReleasesList.indexOf(newfRelease);
  	
  	$timeout(function() {
  		console.log(idx);
  		$timeout(function() { $scope.gridFutureReleases.selectRow(idx, true); });
  	}); 
  	restResource.post(newfRelease);
  	
  };
  
  $scope.save = function(){
  	console.log("view6Controller: save action");
  	if($scope.futureReleasesSelected[0] == null)
  		return;
  	fRelease = $scope.futureReleasesSelected[0];
  	restResource.put(fRelease);	
  };
  
  $scope.cancel = function(){
  	console.log("view6Controller: cancel action");
  };    
  
  $scope.remove = function(){
  	console.log("view6Controller: remove action");
  	fRelease = $scope.futureReleasesSelected[0];
  	link = fRelease.link.href;
  	
  	// remove local model
  	var idx = $scope.futureReleasesList.indexOf(fRelease);      	
  	
  	restResource.remove(fRelease).then(function(data){
  		$scope.futureReleasesList.splice(idx, 1);
  		$timeout(function() { $scope.gridFutureReleases.selectRow(0, true); });    		
  	});
  	
  };    
  
  $scope.go = function ( path ) {
  	  $location.path( path );
  };
}]);

//view7 controller
app.controller('view7Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view7Controller");
	
    $scope.gridFutureReleasesDetail = { 
      data: 'futureReleasesList',
      selectedItems: $scope.futureReleasesSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      width: 600,
      columnDefs: [{ field: "featureId", width: 120, displayName: 'ID', enableCellEdit: true},
                   { field: "name", width: 180 , displayName: 'Name', enableCellEdit: true },
                   { field: "category", width: 300 , displayName: 'Category', enableCellEdit: true },
                   { field: "description", width: 300 , displayName: 'Description', enableCellEdit: true }
                   ]
    };		
	
	// projects data model
    futureReleasesRestURL = $scope.futureReleasesSelected[0].link.href;
	console.log("futureReleasesRestURL:::: " + futureReleasesRestURL);
	//
	restResource.get(futureReleasesRestURL).then(function (data) {
		console.log(data);
		$scope.fRelease = data;
		$scope.futureReleasesList = $scope.fRelease.feature;
		console.log("check resource: ");
		console.log(restResource.entity);
	});
	//
	$scope.go = function ( path ) {
  	  $location.path( path );
	};
	//
	$scope.save = function(){
    	console.log("view7Controller: save action");
    	console.log($scope.fRelease);
    	if($scope.fRelease == null)
    		return;  	
    	restResource.put();
    };
	
}]);


//view8 controller
app.controller('view8Controller', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("view8Controller");

	
	$scope.gridUpdates = { 
		      data: 'updatesList',
		      selectedItems: $scope.updatesSelected,
		      enablePaging: true,
		      multiSelect: false,
		      resizable: true,
		      showFilter: true, showColumnMenu:true,
		      sortInfo: { fields: ['patchId'], directions: ['asc'] },
		      filterOptions: $scope.filterOptions,
		      heigh: 100,
		      columnDefs: [{ field: "patchId", width: 120, displayName: 'No.'},
		                   { field: "patchNotes", width: 300 , displayName: 'Patch notes' },
		                   { field: "patchVersion", width: 170 , displayName: 'Version' },
		                   { field: "patchDate", width: 170 , displayName: 'Patch date' },
		                   { field: "product.applicationName", width: 300 , displayName: 'Patch for' }]   
		    };		
	
	$http.get(updatesRestURL)
	.success(function(data){
		console.log("view8Controller:GET URL : " + updatesRestURL);
		$scope.updatesList = data;
	})
	.error(function(data){
		console.log('ERROR');
		console.log(data);
	});		
	
	$scope.view8_name = "Updates list";
}]);

//view9 controller
app.controller('view9Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view9Controller");
	
    $scope.gridUpdates = { 
      data: 'updatesList',
      selectedItems: $scope.updatesSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      sortInfo: { fields: ['patchId'], directions: ['asc'] },
      heigh: 100,
      columnDefs: [{ field: "patchId", width: 120, displayName: 'No.'},
                   { field: "patchNotes", width: 300 , displayName: 'Patch notes' },
                   { field: "patchVersion", width: 170 , displayName: 'Version' },
                   { field: "patchDate", width: 170 , displayName: 'Patch date' },
                   { field: "product.applicationName", width: 300 , displayName: 'Patch for' }]  
    };

	restResource.get(updatesRestURL).then(function (data) {
		console.log(data);
		$scope.updatesList = data;
		$timeout(function() {
			idx = 0;
			console.log("patch to select [back]: ");
			console.log($scope.updatesSelected[0]);			
			if ($scope.updatesSelected[0] != null){
				for(i in $scope.updatesList){
					if ($scope.updatesList[i].patchId == $scope.updatesSelected[0].patchId)
						idx = i;
				}
			}
			try{ $scope.gridUpdates.selectRow(idx, true); } catch(e){}
		});
	});	    
    
    $scope.add = function(){
    	console.log("view9Controller: add action");
    	patch = $scope.updatesSelected[0];
    	newPatch = JSON.parse(JSON.stringify(patch));
 
    	newPatch.patchId=patch.patchId;
    	newPatch.patchNotes=patch.patchNotes;
    	newPatch.patchVersion=patch.patchVersion;
    	newPatch.patchDate=patch.patchDate;
    	newPatch.product=[];
    	newPatch.release=[];
    	
    	newPatch.link.href = newPatch.link.href.replace(patch.applicationId, newPatch.applicationId);
    	
    	//
    	$scope.updatesList.push(newPatch);
    	idx = $scope.updatesList.indexOf(newPatch);
    	
    	$timeout(function() {
    		console.log(idx);
    		$timeout(function() { $scope.gridUpdates.selectRow(idx, true); });
    	}); 
    	restResource.post(newPatch);
    	
    };
    
    $scope.save = function(){
    	console.log("view9Controller: save action");
    	if($scope.updatesSelected[0] == null)
    		return;
    	patch = $scope.updatesSelected[0];
    	restResource.put(patch);	
    };
    
    $scope.cancel = function(){
    	console.log("view9Controller: cancel action");
    };    
    
    $scope.remove = function(){
    	console.log("view9Controller: remove action");
    	patch = $scope.updatesSelected[0];
    	link = patch.link.href;
    	
    	// remove local model
    	var idx = $scope.updatesList.indexOf(patch);      	
    	
    	restResource.remove(patch).then(function(data){
    		$scope.updatesList.splice(idx, 1);
    		$timeout(function() { $scope.gridUpdates.selectRow(0, true); });    		
    	});
    	
    };    
    
    $scope.go = function ( path ) {
    	  $location.path( path );
    };
}]);

//view10 controller
app.controller('view10Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view10Controller");
	
    $scope.detailUpdate = { 
      data: 'updatesList',
      selectedItems: $scope.updatesSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      width: 600,
      columnDefs: [{ field: "featureId", width: 120, displayName: 'ID', enableCellEdit: true},
                   { field: "name", width: 180 , displayName: 'Name', enableCellEdit: true },
                   { field: "category", width: 300 , displayName: 'Category', enableCellEdit: true },
                   { field: "description", width: 300 , displayName: 'Description', enableCellEdit: true }
                   ]
    };		
	
	// projects data model
    updatesRestURL = $scope.updatesSelected[0].link.href;
	console.log("softwaresRestURL:::: " + updatesRestURL);
	//
	restResource.get(updatesRestURL).then(function (data) {
		console.log(data);
		$scope.patch = data;
		$scope.updatesList = $scope.patch.feature;
		console.log("check resource: ");
		console.log(restResource.entity);
	});
	//
	$scope.go = function ( path ) {
  	  $location.path( path );
	};
	//
	$scope.save = function(){
    	console.log("view10Controller: save action");
    	console.log($scope.patch);
    	if($scope.patch == null)
    		return;  	
    	restResource.put();
    };
	
}]);

//view11 controller
app.controller('view11Controller', 
  ['$scope', '$http', '$timeout', 'restResource',	// dependencies
  function($scope, $http, $timeout, restResource) {	// implementation
	console.log("view11Controller");

	
	$scope.gridTeams = { 
		      data: 'teamsList',
		      selectedItems: $scope.teamsSelected,
		      enablePaging: true,
		      multiSelect: false,
		      resizable: true,
		      showFilter: true, showColumnMenu:true,
		      sortInfo: { fields: ['teamId'], directions: ['asc'] },
		      filterOptions: $scope.filterOptions,
		      heigh: 100,
		      columnDefs: [{ field: "teamId", width: 120, displayName: 'Team no.'},
		                   { field: "teamName", width: 300 , displayName: 'Team name' },
		                   { field: "domain", width: 170 , displayName: 'Domain field' },
		                   { field: "competence", width: 200 , displayName: 'Competence' }]   
		    };		
	
	$http.get(teamsRestURL)
	.success(function(data){
		console.log("view11Controller:GET URL : " + teamsRestURL);
		$scope.teamsList = data;
	})
	.error(function(data){
		console.log('ERROR');
		console.log(data);
	});		
	
	$scope.view11_name = "Teams list";
}]);

//view12 controller
app.controller('view12Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view12Controller");
	
    $scope.gridTeams = { 
      data: 'teamsList',
      selectedItems: $scope.teamsSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      sortInfo: { fields: ['teamId'], directions: ['asc'] },
      heigh: 100,
      columnDefs: [{ field: "teamId", width: 120, displayName: 'Team no.'},
                   { field: "teamName", width: 300 , displayName: 'Team name' },
                   { field: "domain", width: 170 , displayName: 'Domain field' },
                   { field: "competence", width: 200 , displayName: 'Competence' }]   
    };

	restResource.get(teamsRestURL).then(function (data) {
		console.log(data);
		$scope.teamsList = data;
		$timeout(function() {
			idx = 0;
			console.log("Team to select [back]: ");
			console.log($scope.teamsSelected[0]);			
			if ($scope.teamsSelected[0] != null){
				for(i in $scope.teamsList){
					if ($scope.teamsList[i].teamId == $scope.teamsSelected[0].teamId)
						idx = i;
				}
			}
			try{ $scope.gridTeams.selectRow(idx, true); } catch(e){}
		});
	});	    
    
    $scope.add = function(){
    	console.log("view12Controller: add action");
    	team = $scope.teamsSelected[0];	
    	newTeam = JSON.parse(JSON.stringify(team));
    	newTeam.teamId=team.teamId;
    	newTeam.teamName=team.teamName;
    	newTeam.domain=team.domain;
    	newTeam.competence=team.competence;
    	newTeam.member=[];
    	newTeam.link.href = newTeam.link.href.replace(team.teamId, newTeam.teamId);
    	
    	//
    	$scope.teamsList.push(newTeam);
    	idx = $scope.teamsList.indexOf(newTeam);
    	
    	$timeout(function() {
    		console.log(idx);
    		$timeout(function() { $scope.gridTeams.selectRow(idx, true); });
    	}); 
    	restResource.post(newTeam);
    	
    };
    
    $scope.save = function(){
    	console.log("view12Controller: save action");
    	if($scope.teamsSelected[0] == null)
    		return;
    	team = $scope.teamsSelected[0];
    	restResource.put(team);	
    };
    
    $scope.cancel = function(){
    	console.log("view12Controller: cancel action");
    };    
    
    $scope.remove = function(){
    	console.log("view12Controller: remove action");
    	team = $scope.teamsSelected[0];
    	link = team.link.href;
    	
    	// remove local model
    	var idx = $scope.teamsList.indexOf(team);      	
    	
    	restResource.remove(team).then(function(data){
    		$scope.teamsList.splice(idx, 1);
    		$timeout(function() { $scope.gridTeams.selectRow(0, true); });    		
    	});
    	
    };    
    
    $scope.go = function ( path ) {
    	  $location.path( path );
    };
}]);

//view13 controller
app.controller('view13Controller', 
  ['$scope', '$http', '$timeout', '$location', 'restResource',	// dependencies
  function($scope, $http, $timeout, $location, restResource) {	// implementation
	console.log("view13Controller");
	
    $scope.detailTeams = { 
      data: 'teamsList',
      selectedItems: $scope.teamsSelected,
      enablePaging: true,
      multiSelect: false,
      resizable: true,
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      width: 600,
      columnDefs: [{ field: "memberId", width: 130, displayName: 'ID', enableCellEdit: true},
                   { field: "memberName", width: 340 , displayName: 'Name', enableCellEdit: true },
                   { field: "role", width: 360 , displayName: 'Role', enableCellEdit: true }]
    };		
	
	// projects data model
    teamsRestURL = $scope.teamsSelected[0].link.href;
	console.log("teamsRestURL:::: " + teamsRestURL);
	//
	restResource.get(teamsRestURL).then(function (data) {
		console.log(data);
		$scope.team = data;
		$scope.teamsList = $scope.team.member;
		console.log("check resource: ");
		console.log(restResource.entity);
	});
	//
	$scope.go = function ( path ) {
  	  $location.path( path );
	};
	//
	$scope.save = function(){
    	console.log("view13Controller: save action");
    	console.log($scope.team);
    	if($scope.team == null)
    		return;  	
    	restResource.put();
    };
	
}]);