//18289 - Mishelle Sousa
//19351 - Carolina Quiterio
import java.util.StringTokenizer;
import java.io.*;

public class Arquivo
{
	private static BufferedReader arquivo;
	private String nome;

	public Arquivo(String nome)
	{
		try
		{
			 this.nome = nome;
			 arquivo =
             new BufferedReader (
             new FileReader (
             nome));

    	}
        catch (Exception erro)
        {
			System.out.println(erro.getMessage());
	    }
	 }

	private boolean valida(Arquivo arq) throws Exception
	{
		if (arq == null || arq.getUmString() == "")
			return false;

		return true;
	}

	public int numeroDeLinhas() throws Exception
	{
		fechaOArquivo();
		arquivo = new BufferedReader (
             new FileReader (
             nome));

		int ret = 0;

		for(; getUmString() != null;)
			ret ++;

		return ret;
	}

	public boolean fimDoArquivo() throws Exception
	{
		return !arquivo.ready();
	}

	public void fechaOArquivo() throws Exception
	{
		try
		{
			this.arquivo.close();
		}
		catch(Exception erro)
		{
			throw new Exception("Erro ao fechar o arquivo");
		}
	}

    public static String getUmString ()
    {
        String ret = null;

        try
        {
            ret = arquivo.readLine();
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro

        return ret;
    }

    public static byte getUmByte () throws Exception
    {
        byte ret = (byte)0;

        try
        {
            ret = Byte.parseByte (arquivo.readLine ());
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro
        catch (NumberFormatException erro)
        {
            throw new Exception ("Byte invalido!");
        }

        return ret;
    }

    public static short getUmShort () throws Exception
    {
        short ret = (short)0;

        try
        {
            ret = Short.parseShort (arquivo.readLine ());
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro
        catch (NumberFormatException erro)
        {
            throw new Exception ("Short invalido!");
        }

        return ret;
    }

    public static int getUmInt () throws Exception
    {
        int ret = 0;

        try
        {
            ret = Integer.parseInt (arquivo.readLine ());
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro
        catch (NumberFormatException erro)
        {
            throw new Exception ("Int invalido!");
        }

        return ret;
    }

    public static long getUmLong () throws Exception
    {
      //long ret=(long)0;
      //long ret=0;
        long ret = 0L;

        try
        {
            ret = Long.parseLong (arquivo.readLine ());
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro
        catch (NumberFormatException erro)
        {
            throw new Exception ("Long invalido!");
        }

        return ret;
    }

    public static float getUmFloat () throws Exception
    {
      //float ret=0;
      //float ret=(float)0.0;
        float ret = 0.0F;

        try
        {
            ret = Float.parseFloat (arquivo.readLine ());
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro
        catch (NumberFormatException erro)
        {
            throw new Exception ("Float invalido!");
        }

        return ret;
    }

    public static double getUmDouble () throws Exception
    {
      //double ret=0;
      //double ret=(long)0;
      //double ret=0L;
        double ret = 0.0;

        try
        {
            ret = Double.parseDouble (arquivo.readLine ());
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro
        catch (NumberFormatException erro)
        {
            throw new Exception ("Double invalido!");
        }

        return ret;
    }

    public static char getUmChar () throws Exception
    {
        char ret=' ';

        try
        {
            String str = arquivo.readLine ();

            if (str == null)
                throw new Exception ("Char invalido!");

            if (str.length() != 1)
                throw new Exception ("Char invalido!");

             ret = str.charAt(0);
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro

        return ret;
    }

    public static boolean getUmBoolean () throws Exception
    {
        boolean ret = false;

        try
        {
            String str = arquivo.readLine ();

            if (str == null)
                throw new Exception ("Boolean invalido!");

            if (!str.equals("true") && !str.equals("false"))
                throw new Exception ("Boolean invalido!");

            ret = Boolean.parseBoolean (str);
        }
        catch (IOException erro)
        {} // sei que nao vai dar erro

        return ret;
    }
}

//se as incogntas forem letras
//se 1ro num do arr for != do num de linhas-1   ok
//se tiverem eqs repetidas                      ok
