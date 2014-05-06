package game;

public class Organism {
	
	private int status; // 0: vacío; 1: muerto; 2: vivo; 3: revivido;
	private int adjacent;
	
	Organism(){
		status = 0;
		this.adjacent = 0;
	}
	
	public void setAdjacent(int n){
		this.adjacent = n;
	}
	
	public int getStatus(){
		return this.status;
	}
	public boolean isAlive(){
		switch(this.status){
			case 0:
			case 1:
			default: return false; 
			case 2:
			case 3: return true;
		}
	}
	
	public void organismIsPrimitiveLive(){
		this.status = 2;
	}
	private void organismRevive(){
		this.status = 3;
	}
	private void organismKill(){
		this.status = 1;
	}
	
	public void updateStatus(){
		if(!isAlive() && this.adjacent == 3)
			organismRevive();
		else if(
			isAlive() && this.adjacent <= 1 ||
			isAlive() && this.adjacent >= 4)
			organismKill();
	}

}
