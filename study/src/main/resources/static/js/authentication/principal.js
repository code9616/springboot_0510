/**
 *  세션정보 요청
 */


 async function getAuthenticationReq(){
	const url = "/api/v1/authentication";
	
	const response = await fetch(url);
	if(response.ok){
		return response.json();  
	}else{
		throw new Error("Failed to get Authentication." + response);
	}
}