package br.edu.utfpr.dv.sireata.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

import br.edu.utfpr.dv.sireata.bo.AtaBO;
import br.edu.utfpr.dv.sireata.bo.CampusBO;
import br.edu.utfpr.dv.sireata.bo.DepartamentoBO;
import br.edu.utfpr.dv.sireata.bo.OrgaoBO;
import br.edu.utfpr.dv.sireata.model.Ata;
import br.edu.utfpr.dv.sireata.model.Campus;
import br.edu.utfpr.dv.sireata.model.Departamento;
import br.edu.utfpr.dv.sireata.model.Orgao;
import br.edu.utfpr.dv.sireata.util.ExtensionUtils;

public class AtaPublicadaView extends CustomComponent implements View {
	
	public static final String NAME = "ataspublicadas";
	
	private final VerticalLayout layout;
	
	public AtaPublicadaView() {
		//this.setSizeFull();
		this.layout = new VerticalLayout();
		this.layout.setSizeFull();
		this.layout.setSpacing(true);
		this.setCompositionRoot(this.layout);
	}
	
	private void carregaAtas(int idOrgao){
		this.layout.removeAllComponents();
		
		try{
			Orgao orgao = new OrgaoBO().buscarPorId(idOrgao);
			Departamento departamento = new DepartamentoBO().buscarPorId(orgao.getDepartamento().getIdDepartamento());
			Campus campus = new CampusBO().buscarPorId(departamento.getCampus().getIdCampus());
			
			List<Ata> atas = new AtaBO().listarPorOrgao(idOrgao);
			
			this.layout.addComponent(new Label("�rg�o: " + orgao.getNome()));
			this.layout.addComponent(new Label("Departamento/Coordena��o: " + departamento.getNome()));
			this.layout.addComponent(new Label("C�mpus: " + campus.getNome()));
			
			for(Ata ata : atas){
				String data = new SimpleDateFormat("dd/MM/yyyy").format(ata.getData());
				String numero = ata.getNumero() + "/" + new SimpleDateFormat("yyyy").format(ata.getData());
				Link link = new Link(data + " - Ata " + numero + " - " + ata.getTipo().toString(), null);
				
				new ExtensionUtils().extendToDownload("Ata " + numero + " - " + data + ".pdf", ata.getDocumento(), link);
				
				this.layout.addComponent(link);
			}
		}catch(Exception e){
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			
			this.layout.addComponent(new Label("N�o foi poss�vel carregar as atas. " + e.getMessage()));
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if(event.getParameters() != null){
			this.carregaAtas(Integer.parseInt(event.getParameters()));
		}else{
			this.carregaAtas(0);
		}
	}

}
