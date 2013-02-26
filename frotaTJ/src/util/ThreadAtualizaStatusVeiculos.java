package util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import dao.TipoServicoModelo;
import dao.Veiculo;
import mb.MBTipoServiçoModelo;
import mb.MBVeiculo;

public class ThreadAtualizaStatusVeiculos implements Runnable {

	public void run(){

		MBVeiculo mbVeiculo = MBVeiculo.getInstance();
		MBTipoServiçoModelo mbTSM = MBTipoServiçoModelo.getInstance();

		try {		
			List<Veiculo> veiculos = mbVeiculo.listarVeiculos();
			for (int i=0;i<veiculos.size();i++){
				mbTSM.atualizaStatusVeiculo(veiculos.get(i));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
