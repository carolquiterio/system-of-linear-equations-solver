//18289 - Mishelle Sousa
//19351 - Carolina Quiterio
import java.io.*;
import java.util.StringTokenizer;
public class Matriz implements Cloneable
{
    private int numeroDeIncognitas;
    private double matriz[][];
	Arquivo arquivo;

	public Matriz(int qtd, Arquivo arquivo) throws Exception
	{
		//if(matriz == null)
		//	throw new Exception("Matriz nula!");
		numeroDeIncognitas = qtd;
		matriz = new double[qtd][qtd + 1];
		formarMatriz();
		this.arquivo = arquivo;
	}

	public boolean valida(int numero) throws Exception
	{
		if(numero != (arquivo.numeroDeLinhas() - 1))
			return false;


		double resto = -1;

		int avnc = 1;
		for(int a = 0; a < this.matriz.length - 1; a++)
		{
			for(int b = avnc; b < this.matriz.length; b++)
			{
				for(int c = 0; c < this.matriz.length; c++)
				{
					if(this.matriz[a][c] == 0 && this.matriz[b][c] != 0 ||
					   this.matriz[b][c] == 0 && this.matriz[a][c] != 0)
					   break;
					if(this.matriz[a][c] != 0)
					{
							 resto = this.matriz[a][c] / this.matriz[b][c];
							 if(this.vetorIgual(a, b, resto))
							 return false;

						 	break;
					}
				}
				resto = 0;
			}
			avnc++;
		}
		return true;
	}

	public boolean vetorIgual(int a, int b, double resto)
	{
		for(int i = 0; i < this.tamanhoDeColunas(); i++)
		{


			if(this.matriz[a][i]  == 0 && this.matriz[b][i] != 0)
				return false;

			if(this.matriz[b][i] == 0 && this.matriz[a][i] != 0)
				return false;
				if(this.matriz[a][i] * resto != this.matriz[b][i])
								return false;

							if(this.matriz[b][i] * resto != this.matriz[a][i])
						return false;
		}

		return true;
	}
	public String toString()
	{
		String ret = "";

		for(int i = 0; i < this.matriz.length; i++)
		{
			for(int j = 0; j <= this.matriz[0].length; j++)
			{
				if(j  ==  this.matriz.length)
					ret = ret + "A "  + (i + 1) + "º incognita vale: "  +  this.matriz[i][j] + "\n";
			}
		}
		return ret;
	}

	public double[] getUmaLinha(int linha) throws Exception         //chamar valida!!!!!!!!!!!!!!!!!!!!
	{
		if (linha < 0 || linha > this.tamanho())
			throw new Exception("Numero de linhas inválido!");

		double[] ret = new double[this.tamanhoDeColunas()];
		for (int j = 0; j < numeroDeIncognitas; j++)
		{
			ret[j] = this.matriz[linha][j];
		}
		return ret;
	}

	public void setUmaLinha(int linha, int[] linhaNova) throws Exception  //chamar valida!!!!!!!!!!!!!!!!!!!!
	{
		if (linha < 0 || linha > this.tamanhoDeColunas())
			throw new Exception("Numero de linhas inválido!");

		for (int j = 0; j < numeroDeIncognitas; j++)
		{
			this.matriz[linha][j] = linhaNova[j];
		}
	}

	public double[] getUmaColuna(int coluna) throws Exception          //chamar valida!!!!!!!!!!!!!!!!!!!!
	{
		if (coluna < 0 || coluna > this.tamanhoDeColunas())
			throw new Exception("Numero de colunas inválido!");

		double[] ret = new double[this.tamanhoDeColunas()];
		for (int i = 0; i < this.tamanhoDeColunas(); i++)
		{
			ret[i] = this.getMatriz(i, coluna);
		}
		return ret;
	}

	public void formarMatriz() throws Exception  // quebra os pedaçinhos do arquivo e coloca numa matriz
	{
		try
		{
			for (int i = 0; i < this.tamanho(); i++)
			{
				StringTokenizer quebrador = new StringTokenizer (arquivo.getUmString());
				int j = 0;
			    while (quebrador.hasMoreTokens())
			    {
					String token = quebrador.nextToken();
					this.matriz[i][j] = Integer.parseInt(token);
					j++;
				}
			}
		}
		catch(Exception erro)
		{}
	}

    public double getMatriz(int i, int j) throws Exception  //retorna um elemento da matriz
	{
		if(i < 0 || j < 0 || i > this.tamanhoDeColunas() || j > this.tamanhoDeColunas())
			throw new Exception("Passe os parametros corretos!");

		return this.matriz[i][j];
	}

	public void setMatriz(int i, int j, double valor) throws Exception  //set um elemento da matriz      //////////////////////////CHAMAR O VALIDA AQUI!!!!
	{
		if(i < 0 || j < 0 || i > this.tamanhoDeColunas() || j > this.tamanhoDeColunas())
			throw new Exception("Passe os parametros corretos!");

		this.matriz[i][j] = valor;
	}

	public void organizaLinhas() throws Exception  //organiza as linhas da matriz até não ter zeros nas diagonais
	{
		try
		{
			int cont = 0;
			while(temZeroLinhaLinha() && cont != this.tamanho())
			{
				double[] primeiraLinha = new double[this.tamanhoDeColunas()];

				for(int i = 0; i < this.tamanhoDeColunas(); i++)
				{
					 primeiraLinha[i] = this.getMatriz(0, i);
				}

				for(int linha = 0; linha < this.tamanho()-1; linha++)
				{
					double[] linhaDeBaixo = this.matriz[linha + 1];
					for(int j = 0; j < this.tamanhoDeColunas(); j++)
					{
						this.matriz[linha][j] = linhaDeBaixo[j];
					}
				}

				for(int j = 0; j < this.tamanhoDeColunas(); j++)
				{
					this.matriz[this.tamanho()-1][j] = primeiraLinha[j];
				}

				cont++;

				if(cont == this.tamanho())
					throw new Exception("Sistema sem solução!\n");
			}
		}
		catch(Exception erro)
		{
			throw new Exception("Impossivel reorganizar as linhas!\n");
		}
	}

	private boolean temZeroLinhaLinha()  //verifica se tem 0 na diagonal
	{
		try
		{
			for(int linha = 0; linha < this.tamanho(); linha++)
			{
				if(this.matriz[linha][linha] == 0)
					return true;
			}
		}
		catch(Exception erro)
		{}
		return false;
	}

	public void divideSe(int linha) throws Exception  //transforma as diagonais em 1
	{
		try
		{
			double valor = this.getMatriz(linha, linha);
			if(valor != 1.0)
			{
				for(int j = 0; j < this.tamanhoDeColunas(); j++)
				{
					this.setMatriz(linha, j, (this.getMatriz(linha, j) / valor));
				}
			}
		}
		catch(Exception erro)
		{
			throw new Exception("Deu ao transformar as diagonais em 1!");
		}
	}

	public void multipliqueSe(int coluna) throws Exception  //transforma as colunas em 0
	{
		try
		{
			for(int linha = 0; linha < this.tamanho(); linha++)
			{
				double valor = this.getMatriz(linha, coluna);
				if((valor != 0) && (linha != coluna))
				{
					for(int j = 0; j < this.tamanhoDeColunas(); j++)
					{
						double aux = this.getMatriz(linha, j) + (this.getMatriz(coluna, j)* - valor);
						this.setMatriz(linha, j, aux);
					}
				}
			}
		}
		catch(Exception erro)
		{
			throw new Exception("Deu erro ao transformar as colunas em 0!");
		}
	}

	public int tamanho()
	{
		return this.matriz.length;
	}

	public int tamanhoDeColunas()
	{
		return this.matriz[0].length;
	}

	public void resolve() throws Exception  //cahma os métodos de resoluçao e resolve o sistema
	{
		try
		{
			this.organizaLinhas();
			for(int i = 0; i < this.tamanho(); i++)
			{
				this.divideSe(i);
				this.multipliqueSe(i);
			}
		}
		catch(Exception erro)
		{} // sei que não vai dar erro
	}

	public int hashCode()
	{
		int ret = 1;
		ret = ret * 7 + new Double (this.numeroDeIncognitas).hashCode();

		for (int i = 0; i<this.numeroDeIncognitas * (numeroDeIncognitas + 1); i++)
		{
			for (int j = 0; j<this.numeroDeIncognitas * (numeroDeIncognitas + 1); j++)
				ret = ret * 7 + new Double (this.matriz[i][j]).hashCode();
		}

		if (ret<0)
			ret=-ret;

			return ret;
	}

	public boolean equals (Object obj)
	{
		if (this==obj)
			return true;

		if (obj==null)
			return false;

		if (this.getClass()!=obj.getClass())
			return false;

		Matriz mat = (Matriz)obj;

	    if (this.numeroDeIncognitas != mat.numeroDeIncognitas )
			    return false;

		for (int i = 0; i < this.matriz.length; i++)  //arrumar
		{
			for (int j = 0; j < this.matriz.length; j++)
			{
				if (this.matriz[i][j] != mat.matriz[i][j])
					return false;
			}
		}
		return true;
    }

    public Matriz (Matriz modelo) throws Exception
	{
		if (modelo == null)
		    throw new Exception ("Modelo ausente");

	       this.numeroDeIncognitas = modelo.numeroDeIncognitas;

	       this.matriz = new double [modelo.matriz.length][modelo.matriz.length + 1];

	       for (int i = 0 ; i < this.numeroDeIncognitas; i++)
	       {
			   for (int j = 0 ; j < this.numeroDeIncognitas; j++)
			   {
		           this.matriz[i][j] = modelo.matriz[i][j];
			   }
	       }
	}

	public Object clone ()
	{
		Matriz ret = null;

		try
        {
	    	ret = new Matriz (this);
		}
		catch (Exception erro)
		{} // sei que não vai dar erro

		return ret;
	}

	/*public String toString ()  // monta uma matriz visual
		{
			S1tring ret = "";
			for (int i = 0; i < this.tamanho(); i++)
			{
				for (int j = 0; j < this.tamanhoDeColunas(); j++)
				{
					ret = ret + matriz[i][j] + " ";
				}
				ret += "\n";
			}

			return ret;
	}*/
}
