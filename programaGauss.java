//18289 - Mishelle Sousa
//19351 - Carolina Quiterio
import java.io.*;
public class programaGauss
{
	public static void main (String[] args) throws Exception
	{
		try
		{
			System.out.println("Digite o nome do arquivo \n");
			String entrada = Teclado.getUmString();

			Arquivo arquivo = new Arquivo(entrada);//instanciamos a classe Arquivo (classe principal, pois é dai que lemos o arquivo)
			int quantasIncognitas = arquivo.getUmInt();

			Matriz sistemaLinear = new Matriz(quantasIncognitas, arquivo);
			if(sistemaLinear.valida(quantasIncognitas))
			{
				sistemaLinear.resolve();
				System.out.println("\n" + sistemaLinear);
			}
			else
			System.out.println("\nMátriz ou número de incognitas inválidos \n");

		}
		catch(Exception erro)
		{
			System.out.println(erro.getMessage());
		}
	}
}
